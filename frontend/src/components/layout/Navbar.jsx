import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { NavLink, useNavigate } from 'react-router-dom';
import { logout } from '../../store/slices/authSlice';

function Navbar() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const user = useSelector((state) => state.auth?.user);
  const isAuthenticated = useSelector((state) => state.auth?.isAuthenticated);

  const handleLogout = () => {
    dispatch(logout());
    navigate('/login');
  };

  const linkClass = ({ isActive }) => 'navbar-link' + (isActive ? ' navbar-link-active' : '');

  return (
    <nav className="navbar" aria-label="Main">
      <div className="navbar-inner">
        <div className="navbar-brand" onClick={() => navigate('/')}>
          <span className="navbar-logo" aria-hidden="true">◆</span>
          <h2>LangLoop</h2>
        </div>

        <div className="navbar-links">
          <NavLink to="/" end className={linkClass}>Dashboard</NavLink>
          <NavLink to="/decks" className={linkClass}>Decks</NavLink>
        </div>

        <div className="navbar-actions">
          {user ? (
            <>
              <span className="navbar-user">
                <span className="navbar-avatar">{(user.username || '?').charAt(0).toUpperCase()}</span>
                <span className="navbar-user-info">
                  <span className="navbar-username">{user.username || 'Account'}</span>
                  {user.role && <span className="role-badge">{user.role}</span>}
                </span>
              </span>
              <button className="btn btn-ghost" onClick={handleLogout}>
                Logout
              </button>
            </>
          ) : (
            <>
              <button className="btn btn-ghost" onClick={() => navigate('/login')}>Login</button>
              <button className="btn btn-primary" onClick={() => navigate('/register')}>Sign up</button>
            </>
          )}
        </div>
      </div>

      {isAuthenticated !== undefined && (
        <div className="navbar-mobile-links">
          <NavLink to="/" end className={linkClass}>Dashboard</NavLink>
          <NavLink to="/decks" className={linkClass}>Decks</NavLink>
        </div>
      )}
    </nav>
  );
}

export default Navbar;
