import React, { useEffect, useState } from 'react';
import api from '../../services/api';
import { useParams } from 'react-router-dom';

function StudyMode() {
  const { deckId } = useParams();

  const [cards, setCards] = useState([]);
  const [index, setIndex] = useState(0);

  useEffect(() => {
    const loadCards = async () => {
      try {
        const response = await api.get(`/decks/${deckId}/cards`);
        setCards(response.data || []);
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
    </div>
  );
}

export default StudyMode;