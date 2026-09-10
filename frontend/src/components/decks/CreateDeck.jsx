import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import api from '../../services/api';

function CreateDeck() {
  const navigate = useNavigate();
  const { id } = useParams();
  const isEditMode = Boolean(id);

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [capacity, setCapacity] = useState(20);
  const [languageTrack, setLanguageTrack] = useState('');
  const [isPublic, setIsPublic] = useState(false);

  const [languages, setLanguages] = useState([]);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [submitting, setSubmitting] = useState(false);
  const [loadingDeck, setLoadingDeck] = useState(isEditMode);

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

  useEffect(() => {
    if (!isEditMode) return;

    const loadDeck = async () => {
      try {
        const response = await api.get(`/decks/${id}`);
        const deck = response.data || {};

        setTitle(deck.title || '');
        setDescription(deck.description || '');
        setCapacity(deck.capacity || 20);
      } catch (err) {
        setError(err?.response?.data?.message || 'Failed to load deck for editing.');
      } finally {
        setLoadingDeck(false);
      }
    };

    loadDeck();
  }, [id, isEditMode]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    setMessage('');
    setError('');
    setSubmitting(true);

    const payload = {
      title,
      description,
      capacity: Number(capacity)
    };

    try {
      const response = isEditMode
        ? await api.put(`/decks/${id}`, payload)
        : await api.post('/decks', payload);

      setMessage(
        isEditMode
          ? 'StudyDeck updated successfully.'
          : 'StudyDeck created successfully.'
      );

      const targetId = response.data?.id || id;
      setTimeout(() => navigate(`/decks/${targetId}`), 700);
    } catch (err) {
      setError(
        err?.response?.data?.message ||
        (isEditMode ? 'Failed to update deck' : 'Failed to create deck')
      );
    } finally {
      setSubmitting(false);
    }
  };

  if (loadingDeck) {
    return (
      <div className="page">
        <p className="loading-text">Loading deck…</p>
      </div>
    );
  }

  return (
    <div className="page page-narrow">
      <div className="page-header">
        <h1>{isEditMode ? 'Edit Deck' : 'New Deck'}</h1>
      </div>

      <form className="card form-card" onSubmit={handleSubmit}>

        <div className="form-field">
          <label htmlFor="deck-title">
            Deck Title
          </label>

          <input
            id="deck-title"
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="e.g., Spanish Vocabulary"
            required
          />
        </div>

        <div className="form-field">
          <label htmlFor="deck-description">
            Description
          </label>

          <textarea
            id="deck-description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="What will learners practice in this deck?"
            rows={3}
          />
        </div>

        <div className="form-field">
          <label htmlFor="deck-capacity">
            Capacity (max cards)
          </label>

          <input
            id="deck-capacity"
            type="number"
            min="1"
            value={capacity}
            onChange={(e) => setCapacity(e.target.value)}
            required
          />
        </div>

        <div className="form-field">
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
        </div>

        <div className="form-field form-field-inline">
          <input
            id="public-deck"
            type="checkbox"
            checked={isPublic}
            onChange={(e) =>
              setIsPublic(e.target.checked)
            }
          />
          <label htmlFor="public-deck">
            Make this deck public
          </label>
        </div>

        {message && (
          <p className="form-success">{message}</p>
        )}

        {error && (
          <p className="form-error">{error}</p>
        )}

        <div className="form-actions">
          <button type="button" className="btn btn-ghost" onClick={() => navigate('/decks')}>
            Cancel
          </button>

          <button type="submit" className="btn btn-primary" disabled={submitting}>
            {submitting ? 'Saving...' : (isEditMode ? 'Save Changes' : 'Create Deck')}
          </button>
        </div>

      </form>
    </div>
  );
}

export default CreateDeck;
