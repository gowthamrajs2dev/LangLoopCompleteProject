import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { login } from '../../store/slices/authSlice';
import { useNavigate, Link } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const { loading, error } = useSelector(
    (state) => state.auth || {}
  );

  const handleSubmit = async (e) => {
    e.preventDefault();

    const result = await dispatch(
      login({ username, password })
    );

    if (login.fulfilled.match(result)) {
      navigate('/');
    }
  };

  return (
    <div className="auth-page">
      <form className="auth-card" onSubmit={handleSubmit}>
        <h1 className="auth-title">Welcome back</h1>
        <p className="auth-subtitle">Log in to keep your streak going.</p>

        <div className="form-field">
          <label htmlFor="username">
            Username
          </label>

          <input
            id="username"
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="e.g., linguist1"
            required
          />
        </div>

        <div className="form-field">
          <label htmlFor="password">
            Password
          </label>

          <input
            id="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Your password"
            required
          />
        </div>

        {error && (
          <p className="form-error">{error}</p>
        )}

        <button className="btn btn-primary btn-block" type="submit" disabled={loading}>
          {loading ? 'Logging in...' : 'Login'}
        </button>

        <p className="auth-switch">
          New to LangLoop? <Link to="/register">Create an account</Link>
        </p>
      </form>
    </div>
  );
}

export default Login;
