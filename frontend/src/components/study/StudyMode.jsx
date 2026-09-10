import React, { useEffect, useMemo, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../../services/api';

const normalizeCard = (raw) => {
  const source = raw?.card ? raw.card : raw;

  return {
    id: source?.id,
    front: source?.frontContent ?? source?.frontText ?? source?.question ?? '',
    back: source?.backContent ?? source?.backText ?? source?.answer ?? ''
  };
};

function StudyMode() {
  const { deckId } = useParams();
  const navigate = useNavigate();

  const [cards, setCards] = useState([]);
  const [index, setIndex] = useState(0);
  const [showAnswer, setShowAnswer] = useState(false);
  const [loading, setLoading] = useState(true);
  const [completed, setCompleted] = useState(0);
  const [submitting, setSubmitting] = useState(false);
  const [sessionDone, setSessionDone] = useState(false);

  useEffect(() => {
    const loadCards = async () => {
      try {
        const response = await api.get(`/decks/${deckId}/cards`);

        const data = response?.data;
        let list = [];

        if (Array.isArray(data)) {
          list = data;
        } else if (Array.isArray(data?.data)) {
          list = data.data;
        } else if (Array.isArray(data?.cards)) {
          list = data.cards;
        }

        setCards(list.map(normalizeCard));
      } catch (error) {
        setCards([]);
      } finally {
        setLoading(false);
      }
    };

    loadCards();
  }, [deckId]);

  const currentCard = cards[index];
  const progressPct = useMemo(
    () => (cards.length ? Math.round((index / cards.length) * 100) : 0),
    [index, cards.length]
  );

  const goNext = () => {
    setShowAnswer(false);

    if (index >= cards.length - 1) {
      setSessionDone(true);
    } else {
      setIndex(index + 1);
    }
  };

  const handleRate = async (quality) => {
    setSubmitting(true);

    try {
      await api.post('/study/complete', {
        // userId is re-resolved server-side from the auth token; the
        // backend still requires a non-null value here for validation.
        userId: 0,
        deckId: Number(deckId),
        score: quality * 20,
        quality
      });
    } catch (error) {
      // Non-fatal: still let the learner move on even if logging failed.
    } finally {
      setCompleted((prev) => prev + 1);
      setSubmitting(false);
      goNext();
    }
  };

  if (loading) {
    return (
      <div className="page">
        <p className="loading-text">Loading study session…</p>
      </div>
    );
  }

  if (cards.length === 0) {
    return (
      <div className="page">
        <div className="empty-state">
          <span className="empty-state-icon">🃏</span>
          <h1>Study Mode</h1>
          <p>No cards available.</p>
          <button className="btn btn-secondary" onClick={() => navigate(-1)}>← Back to deck</button>
        </div>
      </div>
    );
  }

  if (sessionDone) {
    return (
      <div className="page page-narrow">
        <div className="card study-summary">
          <span className="empty-state-icon">🎉</span>
          <h1>Session Complete</h1>
          <p>You reviewed {completed} of {cards.length} cards.</p>
          <div className="form-actions">
            <button className="btn btn-secondary" onClick={() => navigate(`/decks/${deckId}`)}>Back to Deck</button>
            <button className="btn btn-primary" onClick={() => { setIndex(0); setCompleted(0); setSessionDone(false); }}>
              Study Again
            </button>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="page page-narrow">
      <h1>Study Mode</h1>

      <div className="progress-bar">
        <div className="progress-bar-fill" style={{ width: `${progressPct}%` }} />
      </div>
      <p className="progress-label">Card {index + 1} of {cards.length}</p>

      <div className={`flashcard ${showAnswer ? 'flashcard-flipped' : ''}`}>
        <div className="flashcard-face flashcard-front-face">
          <h2>{currentCard.front}</h2>
        </div>

        {showAnswer && (
          <div className="flashcard-face flashcard-back-face">
            <p>{currentCard.back}</p>
          </div>
        )}
      </div>

      {!showAnswer ? (
        <button className="btn btn-primary btn-block" onClick={() => setShowAnswer(true)}>
          Show Answer
        </button>
      ) : (
        <div className="rating-row">
          <span className="rating-hint">How well did you know this?</span>
          <div className="rating-buttons">
            <button className="btn btn-danger" disabled={submitting} onClick={() => handleRate(0)}>Again</button>
            <button className="btn btn-secondary" disabled={submitting} onClick={() => handleRate(2)}>Hard</button>
            <button className="btn btn-secondary" disabled={submitting} onClick={() => handleRate(4)}>Good</button>
            <button className="btn btn-primary" disabled={submitting} onClick={() => handleRate(5)}>Easy</button>
          </div>
        </div>
      )}

      <div className="form-actions">
        {index > 0 && (
          <button className="btn btn-ghost" onClick={() => { setIndex(index - 1); setShowAnswer(false); }}>
            ← Previous
          </button>
        )}
      </div>
    </div>
  );
}

export default StudyMode;
