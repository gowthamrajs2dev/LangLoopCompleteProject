import React, { useEffect } from 'react';
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
    if (!window.confirm('Are you sure?')) {
      return;
    }

    try {
      await api.delete(`/decks/${id}`);

      dispatch(removeDeck(id));

      await api.get('/decks');
    } catch (error) {
      console.error(error);
    }
  };

  const isLinguist =
    user?.role === 'LINGUIST';

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <h1>Study Decks</h1>

      {isLinguist && (
        <button
          onClick={() => navigate('/decks/create')}
        >
          + Create New Deck
        </button>
      )}

      {decks.length === 0 ? (
        <div>
          <p>No Decks Found</p>

          {isLinguist && (
            <button
              onClick={() => navigate('/decks/create')}
            >
              Create Your First Deck
            </button>
          )}
        </div>
      ) : (
        <div>
          {decks.map((deck) => (
            <div key={deck.id}>
              <h2>{deck.title}</h2>

              <button
                onClick={() =>
                  navigate(`/decks/${deck.id}`)
                }
              >
                View
              </button>

              {isLinguist && (
                <button
                  onClick={() => handleDelete(deck.id)}
                >
                  Delete
                </button>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default DeckList;