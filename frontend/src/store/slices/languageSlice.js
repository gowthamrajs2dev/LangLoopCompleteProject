import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  items: [],
  loading: false,
  error: null
};

const languageSlice = createSlice({
  name: 'languages',

  initialState,

  reducers: {
    setLanguages: (state, action) => {
      state.items = action.payload;
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
  setLanguages,
  setLoading,
  setError
} = languageSlice.actions;

export default languageSlice.reducer;