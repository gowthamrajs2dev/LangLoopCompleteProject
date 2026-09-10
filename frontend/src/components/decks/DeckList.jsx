import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';

import {
  setDecks,
  removeDeck
} from '../../store/slices/deckSlice';

function DeckList() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const user = useSelector(
    (state) => state.auth?.user
  );

  const decks = useSelector(
    (state) => state.decks?.items || []
  );

  const loading = useSelector(
    (state) => state.decks?.loading || false
  );

  const [actionError, setActionError] = useState('');

  useEffect(() => {
    const loadDecks = async () => {
      try {
        const response = await api.get('/decks');

        dispatch(
          setDecks(
            Array.isArray(response.data)
              ? response.data
              : []
          )
        );
      } catch (error) {
        dispatch(setDecks([]));
      }
    };

    loadDecks();
  }, [dispatch]);

  const handleDelete = async (id) => {
    if (!window.confirm('Are you sure you want to delete this deck?')) {
      return;
    }

    setActionError('');

    try {
      await api.delete(`/decks/${id}`);
      dispatch(removeDeck(id));
    } catch (error) {
      setActionError(
        error?.response?.status === 403
          ? 'Only admins can delete decks.'
          : (error?.response?.data?.message || 'Failed to delete deck.')
      );
    }
  };

  const canManage = user?.role === 'LINGUIST' || user?.role === 'ADMIN';

  if (loading) {
    return (
      <div className="page">
        <p className="loading-text">Loading decks…</p>
      </div>
    );
  }

  return (
    <div className="page">
      <div className="page-header">
        <div>
          <h1>Study Decks</h1>
          <p className="page-subtitle">Browse every deck, or create your own to share with learners.</p>
        </div>

        {canManage && (
          <button
            className="btn btn-primary"
            onClick={() => navigate('/decks/create')}
          >
            + Create New Deck
          </button>
        )}
      </div>

      {actionError && <p className="form-error">{actionError}</p>}

      {decks.length === 0 ? (
        <div className="empty-state">
          <span className="empty-state-icon">📭</span>
          <p>No Decks Found</p>

          {canManage && (
            <button
              className="btn btn-primary"
              onClick={() => navigate('/decks/create')}
            >
              Create Your First Deck
            </button>
          )}
        </div>
      ) : (
        <div className="deck-grid">
          {decks.map((deck) => (
            <div key={deck.id} className="deck-card">
              <h2>{deck.title}</h2>
              <p className="deck-card-desc">{deck.description || 'No description provided.'}</p>

              <div className="deck-card-footer">
                <span className="badge">{deck.capacity ? `${deck.capacity} cards max` : 'Open capacity'}</span>
                {deck.mentorName && <span className="deck-card-mentor">by {deck.mentorName}</span>}
              </div>

              <div className="deck-card-actions">
                <button
                  className="btn btn-secondary btn-sm"
                  onClick={() => navigate(`/decks/${deck.id}`)}
                >
                  View
                </button>

                {canManage && (
                  <button
                    className="btn btn-ghost btn-sm"
                    onClick={() => navigate(`/decks/${deck.id}/edit`)}
                  >
                    Edit
                  </button>
                )}

                {canManage && (
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() => handleDelete(deck.id)}
                  >
                    Delete
                  </button>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default DeckList;
