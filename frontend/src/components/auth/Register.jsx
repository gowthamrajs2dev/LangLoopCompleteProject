import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, Link } from 'react-router-dom';
import { register } from '../../store/slices/authSlice';

function Register() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('LEARNER');
  const [message, setMessage] = useState('');

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const { loading, error } = useSelector((state) => state.auth || {});

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');

    const result = await dispatch(register({ username, password, role }));

    if (register.fulfilled.match(result)) {
      setMessage('Registration successful. Redirecting...');
      setTimeout(() => navigate('/'), 800);
    }
  };

  return (
    <div className="auth-page">
      <form className="auth-card" onSubmit={handleSubmit}>
        <h1 className="auth-title">Create your account</h1>
        <p className="auth-subtitle">Start building spaced-repetition decks in minutes.</p>

        <div className="form-field">
          <label htmlFor="register-username">Username</label>
          <input
            id="register-username"
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="e.g., linguist_amy"
            required
          />
        </div>

        <div className="form-field">
          <label htmlFor="register-email">Email</label>
          <input
            id="register-email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="you@example.com"
            required
          />
        </div>

        <div className="form-field">
          <label htmlFor="register-password">Password</label>
          <input
            id="register-password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="At least 8 characters"
            required
          />
        </div>

        <div className="form-field">
          <label htmlFor="register-role">I am a</label>
          <select
            id="register-role"
            value={role}
            onChange={(e) => setRole(e.target.value)}
          >
            <option value="LEARNER">Learner — study existing decks</option>
            <option value="LINGUIST">Linguist — create &amp; curate decks</option>
            <option value="ADMIN">Admin — manage the platform</option>
          </select>
        </div>

        {message && <p className="form-success">{message}</p>}
        {error && <p className="form-error">{error}</p>}

        <button className="btn btn-primary btn-block" type="submit" disabled={loading}>
          {loading ? 'Creating account...' : 'Register'}
        </button>

        <p className="auth-switch">
          Already have an account? <Link to="/login">Log in</Link>
        </p>
      </form>
    </div>
  );
}

export default Register;
