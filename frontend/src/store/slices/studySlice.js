import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  cards: [],
  currentIndex: 0,
  loading: false,
  error: null
};

const studySlice = createSlice({
  name: 'study',

  initialState,

  reducers: {
    setCards: (state, action) => {
      state.cards = action.payload;
    },

    setCurrentIndex: (state, action) => {
      state.currentIndex = action.payload;
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
  setCards,
  setCurrentIndex,
  setLoading,
  setError
} = studySlice.actions;

export default studySlice.reducer;