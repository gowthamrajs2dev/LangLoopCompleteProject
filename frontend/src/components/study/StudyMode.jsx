import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from '../../services/api';

function StudyMode() {
  const { deckId } = useParams();

  const [cards, setCards] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [error, setError] = useState('');

  useEffect(() => {
    const loadCards = async () => {
      try {
        const response = await api.get(
          `/decks/${deckId}/cards`
        );

        const data = Array.isArray(response.data)
          ? response.data
          : [];

        setCards(data);
      } catch (err) {
        setError(
          err?.response?.data?.message ||
          'Failed to load study cards'
        );
      }
    };

    loadCards();
  }, [deckId]);

  if (error) {
    return <p>{error}</p>;
  }

  if (cards.length === 0) {
    return (
      <div>
        <h1>Study Mode</h1>
        <p>No cards available.</p>
      </div>
    );
  }

  const item = cards[currentIndex];

  const card = item?.card || item;

  return (
    <div>
      <h1>Study Mode</h1>

      <p>
        Q{currentIndex + 1}
      </p>

      <h2>
        {card?.frontText}
      </h2>

      <p>
        {card?.backText}
      </p>
    </div>
  );
}

export default StudyMode;