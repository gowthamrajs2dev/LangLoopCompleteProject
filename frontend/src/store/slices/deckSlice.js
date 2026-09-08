import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  items: [],
  loading: false,
  error: null
};

const deckSlice = createSlice({
  name: 'decks',

  initialState,

  reducers: {
    setDecks: (state, action) => {
      state.items = action.payload;
    },

    addDeck: (state, action) => {
      state.items.push(action.payload);
    },

    updateDeck: (state, action) => {
      const index = state.items.findIndex(
        (deck) => deck.id === action.payload.id
      );

      if (index !== -1) {
        state.items[index] = action.payload;
      }
    },

    removeDeck: (state, action) => {
      state.items = state.items.filter(
        (deck) => deck.id !== action.payload
      );
    },

    setLoading: (state, action) => {
      state.loading = action.payload;
    },

    setError: (state, action) => {
      state.error = action.payload;
    }
  }
});

export const {
  setDecks,
  addDeck,
  updateDeck,
  removeDeck,
  setLoading,
  setError
} = deckSlice.actions;

export default deckSlice.reducer;