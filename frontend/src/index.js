import React from 'react';
import ReactDOM from 'react-dom/client';
import { Provider } from 'react-redux';
import { configureStore } from '@reduxjs/toolkit';
import { BrowserRouter } from 'react-router-dom';

import App from './App';

import authReducer from './store/slices/authSlice';
import deckReducer from './store/slices/deckSlice';
import languageReducer from './store/slices/languageSlice';
import studyReducer from './store/slices/studySlice';

const store = configureStore({
  reducer: {
    auth: authReducer,
    decks: deckReducer,
    languages: languageReducer,
    study: studyReducer
  }
});

const root = ReactDOM.createRoot(
  document.getElementById('root')
);

root.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>
  </React.StrictMode>
);