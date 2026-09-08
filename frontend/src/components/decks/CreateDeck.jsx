import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';

function CreateDeck() {
  const navigate = useNavigate();

  const [title, setTitle] = useState('');
  const [languageTrack, setLanguageTrack] = useState('');
  const [isPublic, setIsPublic] = useState(false);

  const [languages, setLanguages] = useState([]);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    const loadLanguages = async () => {
      try {
        const response = await api.get('/languages');

        setLanguages(
          Array.isArray(response.data)
            ? response.data
            : []
        );
      } catch (err) {
        setLanguages([]);
      }
    };

    loadLanguages();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    setMessage('');
    setError('');

    try {
      const response = await api.post('/decks', {
        title,
        languageTrackId: languageTrack,
        public: isPublic
      });

      setMessage('StudyDeck created successfully.');

      if (response.data?.id) {
        // Keep the page usable after creation.
      }
    } catch (err) {
      setError(
        err?.response?.data?.message ||
        'Failed to create deck'
      );
    }
  };

  return (
    <div>
      <h1>Create Deck</h1>

      <form onSubmit={handleSubmit}>

        <label htmlFor="deck-title">
          Deck Title
        </label>

        <input
          id="deck-title"
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />

        <label htmlFor="language-track">
          Language Track
        </label>

        <select
          id="language-track"
          value={languageTrack}
          onChange={(e) =>
            setLanguageTrack(e.target.value)
          }
        >
          <option value="">
            Select Language
          </option>

          {languages.map((language) => (
            <option
              key={language.id}
              value={language.id}
            >
              {language.name}
            </option>
          ))}
        </select>

        <label htmlFor="public-deck">
          Make this deck public
        </label>

        <input
          id="public-deck"
          type="checkbox"
          checked={isPublic}
          onChange={(e) =>
            setIsPublic(e.target.checked)
          }
        />

        {message && (
          <p>{message}</p>
        )}

        {error && (
          <p>{error}</p>
        )}

        <button type="submit">
          Create Deck
        </button>

      </form>
    </div>
  );
}

export default CreateDeck;