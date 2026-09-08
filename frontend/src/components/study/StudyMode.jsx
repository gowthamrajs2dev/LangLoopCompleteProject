import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from '../../services/api';

function StudyMode() {
  const { deckId } = useParams();

  const [cards, setCards] = useState([]);
  const [index, setIndex] = useState(0);

  useEffect(() => {
    const loadCards = async () => {
      try {
        const response = await api.get(`/decks/${deckId}/cards`);

        // Handle both:
        // { data: [...] }
        // and { data: { data: [...] } }
        const data = response?.data;

        if (Array.isArray(data)) {
          setCards(data);
        } else if (Array.isArray(data?.data)) {
          setCards(data.data);
        } else if (Array.isArray(data?.cards)) {
          setCards(data.cards);
        } else {
          setCards([]);
        }
      } catch (error) {
        setCards([]);
      }
    };

    loadCards();
  }, [deckId]);

  if (cards.length === 0) {
    return (
      <div>
        <h1>Study Mode</h1>
        <p>No cards available.</p>
      </div>
    );
  }

  const currentCard = cards[index];

  return (
    <div>
      <h1>Study Mode</h1>

      <h2>{currentCard.question}</h2>
      <p>{currentCard.answer}</p>

      {index > 0 && (
        <button onClick={() => setIndex(index - 1)}>
          Previous
        </button>
      )}

      {index < cards.length - 1 && (
        <button onClick={() => setIndex(index + 1)}>
          Next
        </button>
      )}
    </div>
  );
}

export default StudyMode;