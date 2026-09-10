import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../../services/api';

function DeckDetails() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [deck, setDeck] = useState(null);
  const [cards, setCards] = useState([]);
  const [frontText, setFrontText] = useState('');
  const [backText, setBackText] = useState('');

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');
  const [adding, setAdding] = useState(false);

  const loadDeck = async () => {
    try {
      setError('');

      const response = await api.get(
        `/decks/${id}`
      );

      setDeck(response.data);
    } catch (err) {
      setError(
        err?.response?.data?.message ||
        'Failed to load deck details'
      );
    }
  };

  const loadCards = async () => {
    try {
      const response = await api.get(`/decks/${id}/cards`);
      setCards(Array.isArray(response.data) ? response.data : []);
    } catch (err) {
      setCards([]);
    }
  };

  useEffect(() => {
    setLoading(true);
    Promise.all([loadDeck(), loadCards()]).finally(() => setLoading(false));
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  const handleAddCard = async (e) => {
    e.preventDefault();
    setAdding(true);

    try {
      setError('');
      setMessage('');

      await api.post(
        `/decks/${id}/cards`,
        {
          frontContent: frontText,
          backContent: backText,
          frontText,
          backText
        }
      );

      setMessage('Card added successfully.');

      setFrontText('');
      setBackText('');

      await loadCards();
    } catch (err) {
      setError(
        err?.response?.status === 403
          ? 'Only linguists or admins can add cards.'
          : (err?.response?.data?.message || 'Failed to add card')
      );
    } finally {
      setAdding(false);
    }
  };

  if (loading) {
    return (
      <div className="page">
        <p className="loading-text">Loading deck…</p>
      </div>
    );
  }

  return (
    <div className="page">
      <div className="page-header">
        <div>
          <button className="btn-link" onClick={() => navigate('/decks')}>← Back to decks</button>
          <h1>
            {deck?.title || 'Deck Details'}
          </h1>
          {deck?.description && <p className="page-subtitle">{deck.description}</p>}

          <div className="deck-meta">
            {deck?.mentorName && <span className="badge">Curated by {deck.mentorName}</span>}
            {deck?.capacity && <span className="badge badge-outline">{cards.length}/{deck.capacity} cards</span>}
          </div>
        </div>

        <div className="page-header-actions">
          <button
            className="btn btn-primary"
            disabled={cards.length === 0}
            onClick={() => navigate(`/study/${id}`)}
          >
            ▶ Start Studying
          </button>
        </div>
      </div>

      {error && (
        <p className="form-error">{error}</p>
      )}

      <div className="section">
        <h2>Flashcards ({cards.length})</h2>

        {cards.length === 0 ? (
          <p className="empty-hint">No flashcards yet. Add the first one below.</p>
        ) : (
          <div className="card-list">
            {cards.map((card) => (
              <div key={card.id} className="flashcard-row">
                <span className="flashcard-front">{card.frontContent || card.frontText || card.question}</span>
                <span className="flashcard-arrow">→</span>
                <span className="flashcard-back">{card.backContent || card.backText || card.answer}</span>
              </div>
            ))}
          </div>
        )}
      </div>

      <div className="card form-card">
        <h2>Add a Flashcard</h2>

        <form onSubmit={handleAddCard}>
          <div className="form-row">
            <div className="form-field">
              <label htmlFor="front-text">
                Front
              </label>

              <input
                id="front-text"
                placeholder="e.g., Hello"
                value={frontText}
                onChange={(e) =>
                  setFrontText(e.target.value)
                }
                required
              />
            </div>

            <div className="form-field">
              <label htmlFor="back-text">
                Back
              </label>

              <input
                id="back-text"
                placeholder="e.g., Hola"
                value={backText}
                onChange={(e) =>
                  setBackText(e.target.value)
                }
                required
              />
            </div>
          </div>

          <button type="submit" className="btn btn-primary" disabled={adding}>
            {adding ? 'Adding...' : '+ Add to Deck'}
          </button>
        </form>

        {message && (
          <p className="form-success">{message}</p>
        )}
      </div>
    </div>
  );
}

export default DeckDetails;
