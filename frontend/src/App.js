import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Navbar from './components/layout/Navbar';
import Login from './components/auth/Login';
import Register from './components/auth/Register';
import Dashboard from './components/dashboard/Dashboard';
import DeckList from './components/decks/DeckList';
import CreateDeck from './components/decks/CreateDeck';
import DeckDetails from './components/decks/DeckDetails';
import StudyMode from './components/study/StudyMode';

function App() {
  return (
    <>
      <Navbar />

      <Routes>
        <Route
          path="/"
          element={<Dashboard />}
        />

        <Route
          path="/login"
          element={<Login />}
        />

        <Route
          path="/register"
          element={<Register />}
        />

        <Route
          path="/decks"
          element={<DeckList />}
        />

        <Route
          path="/decks/create"
          element={<CreateDeck />}
        />

        <Route
          path="/decks/:id"
          element={<DeckDetails />}
        />

        <Route
          path="/study/:deckId"
          element={<StudyMode />}
        />
      </Routes>
    </>
  );
}

export default App;