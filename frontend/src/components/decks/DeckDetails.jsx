import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from '../../services/api';

function DeckDetails() {
  const { id } = useParams();

  const [deck, setDeck] = useState(null);
  const [frontText, setFrontText] = useState('');
  const [backText, setBackText] = useState('');

  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

  useEffect(() => {
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

    loadDeck();
  }, [id]);

  const handleAddCard = async () => {
    try {
      setError('');
      setMessage('');

      const response = await api.post(
        `/decks/${id}/cards`,
        {
          frontText,
          backText
        }
      );

      setMessage('Card added successfully.');

      setFrontText('');
      setBackText('');
    } catch (err) {
      setError(
        err?.response?.data?.message ||
        'Failed to add card'
      );
    }
  };

  return (
    <div>
      <h1>
        {deck?.title || 'Deck Details'}
      </h1>

      {error && (
        <p>{error}</p>
      )}

      <div>
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
        />

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
        />

        <button onClick={handleAddCard}>
          + Add to Deck
        </button>
      </div>

      {message && (
        <p>{message}</p>
      )}
    </div>
  );
}

export default DeckDetails;