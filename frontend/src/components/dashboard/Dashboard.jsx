import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';

function Dashboard() {
  const navigate = useNavigate();
  const user = useSelector((state) => state.auth?.user);
  const isAuthenticated = useSelector((state) => state.auth?.isAuthenticated);

  const [stats, setStats] = useState({
    totalDecks: 0,
    totalFlashcards: 0,
    masteredCards: 0,
    dueCount: 0,
    streak: 0
  });
  const [recentDecks, setRecentDecks] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    let isMounted = true;

    const loadStats = async () => {
      try {
        const response = await api.get('/dashboard/stats');
        if (!isMounted) return;

        setStats({
          totalDecks: response.data?.totalDecks || 0,
          totalFlashcards: response.data?.totalFlashcards || 0,
          masteredCards: response.data?.masteredCards || 0,
          dueCount: response.data?.dueCount || 0,
          streak: response.data?.streak || 0
        });

        if (Array.isArray(response.data?.recentDecks)) {
          setRecentDecks(response.data.recentDecks);
        }
      } catch (error) {
        await loadStatsFromCoreEndpoints(isMounted);
      } finally {
        if (isMounted) setLoading(false);
      }
    };

    const loadStatsFromCoreEndpoints = async (mounted) => {
      try {
        const decksResponse = await api.get('/decks');
        const decks = Array.isArray(decksResponse.data) ? decksResponse.data : [];

        let dueCount = 0;
        let masteredCards = 0;

        if (isAuthenticated) {
          try {
            const dueResponse = await api.get('/study/due');
            const dueList = Array.isArray(dueResponse.data) ? dueResponse.data : [];
            dueCount = dueList.length;
            masteredCards = dueList.filter((r) => r.masteryLevel === 'MASTERED').length;
          } catch (dueError) {
            // Due reviews require auth; ignore silently for guests/expired sessions.
          }
        }

        if (!mounted) return;

        setStats((prev) => ({
          ...prev,
          totalDecks: decks.length,
          dueCount,
          masteredCards
        }));
        setRecentDecks(decks.slice(0, 5));
      } catch (fallbackError) {
        // Keep the zeroed defaults; the dashboard still renders.
      }
    };

    loadStats();

    return () => {
      isMounted = false;
    };
  }, [isAuthenticated]);

  const isLinguist = user?.role === 'LINGUIST' || user?.role === 'ADMIN';

  return (
    <div className="page">
      <div className="page-header">
        <div>
          <h1>Welcome{user?.username ? `, ${user.username}` : ''} 👋</h1>
          <p className="page-subtitle">Here&apos;s a snapshot of your language learning progress.</p>
        </div>

        <div className="page-header-actions">
          {isLinguist && (
            <button className="btn btn-primary" onClick={() => navigate('/decks/create')}>
              + Create New Deck
            </button>
          )}
          <button className="btn btn-secondary" onClick={() => navigate('/decks')}>
            Browse Decks
          </button>
        </div>
      </div>

      <div className="stat-grid">
        <div className="stat-card">
          <span className="stat-icon">📚</span>
          <h3>Total Decks</h3>
          <p className="stat-value">{loading ? '—' : stats.totalDecks}</p>
        </div>

        <div className="stat-card">
          <span className="stat-icon">🗂️</span>
          <h3>Total Flashcards</h3>
          <p className="stat-value">{loading ? '—' : stats.totalFlashcards}</p>
        </div>

        <div className="stat-card">
          <span className="stat-icon">🏆</span>
          <h3>Mastered Cards</h3>
          <p className="stat-value">{loading ? '—' : stats.masteredCards}</p>
        </div>

        <div className="stat-card stat-card-accent">
          <span className="stat-icon">⏰</span>
          <h3>Due Cards</h3>
          <p className="stat-value">{loading ? '—' : stats.dueCount}</p>
        </div>

        <div className="stat-card">
          <span className="stat-icon">🔥</span>
          <h3>Streak</h3>
          <p className="stat-value">{loading ? '—' : stats.streak}</p>
        </div>
      </div>

      {!isAuthenticated && (
        <div className="callout">
          <p>Log in to track due reviews and start a spaced-repetition study session.</p>
          <button className="btn btn-primary" onClick={() => navigate('/login')}>Log in</button>
        </div>
      )}

      <div className="section">
        <div className="section-header">
          <h2>Recent Decks</h2>
          <button className="btn-link" onClick={() => navigate('/decks')}>View all →</button>
        </div>

        {recentDecks.length === 0 ? (
          <p className="empty-hint">No decks yet. {isLinguist ? 'Create the first one!' : 'Check back soon.'}</p>
        ) : (
          <div className="deck-grid">
            {recentDecks.map((deck) => (
              <div key={deck.id} className="deck-card" onClick={() => navigate(`/decks/${deck.id}`)}>
                <h3>{deck.title}</h3>
                <p className="deck-card-desc">{deck.description || 'No description provided.'}</p>
                <div className="deck-card-footer">
                  <span className="badge">{deck.capacity ? `${deck.capacity} cards max` : 'Open'}</span>
                  {deck.mentorName && <span className="deck-card-mentor">by {deck.mentorName}</span>}
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default Dashboard;
