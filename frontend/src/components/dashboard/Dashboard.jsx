import React, { useEffect, useState } from 'react';
import api from '../../services/api';

function Dashboard() {
  const [stats, setStats] = useState({
    totalDecks: 0,
    totalFlashcards: 0,
    masteredCards: 0,
    dueCount: 0,
    streak: 0
  });

  useEffect(() => {
    const loadStats = async () => {
      try {
        const response = await api.get('/dashboard/stats');

        setStats({
          totalDecks: response.data?.totalDecks || 0,
          totalFlashcards: response.data?.totalFlashcards || 0,
          masteredCards: response.data?.masteredCards || 0,
          dueCount: response.data?.dueCount || 0,
          streak: response.data?.streak || 0
        });
      } catch (error) {
        // keep defaults
      }
    };

    loadStats();
  }, []);

  return (
    <div>
      <h1>Dashboard</h1>

      <div>
        <div>
          <h3>Total Decks</h3>
          <p>{stats.totalDecks}</p>
        </div>

        <div>
          <h3>Total Flashcards</h3>
          <p>{stats.totalFlashcards}</p>
        </div>

        <div>
          <h3>Mastered Cards</h3>
          <p>{stats.masteredCards}</p>
        </div>

        <div>
          <h3>Due Cards</h3>
          <p>{stats.dueCount}</p>
        </div>

        <div>
          <h3>Streak</h3>
          <p>{stats.streak}</p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;