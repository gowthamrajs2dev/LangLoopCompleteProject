import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../store/slices/authSlice';

function Navbar() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const user = useSelector((state) => state.auth?.user);

  const handleLogout = () => {
    dispatch(logout());
    navigate('/login');
  };

  return (
    <nav>
      <div>
        <h2>LangLoop</h2>
      </div>

      <div>
        <button onClick={() => navigate('/')}>
          Dashboard
        </button>

        <button onClick={() => navigate('/decks')}>
          Decks
        </button>

        {user && (
          <span>
            {user.username || user.role}
          </span>
        )}

        <button onClick={handleLogout}>
          Logout
        </button>
      </div>
    </nav>
  );
}

export default Navbar;