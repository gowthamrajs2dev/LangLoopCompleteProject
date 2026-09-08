import React, { useState } from 'react';
import api from '../../services/api';

function Register() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await api.post('/auth/register', {
        username,
        email,
        password
      });

      setMessage('Registration successful.');
    } catch (err) {
      setError(
        err?.response?.data?.message ||
        'Registration failed'
      );
    }
  };

  return (
    <div>
      <h1>Register</h1>

      <form onSubmit={handleSubmit}>

        <label htmlFor="register-username">
          Username
        </label>

        <input
          id="register-username"
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />

        <label htmlFor="register-email">
          Email
        </label>

        <input
          id="register-email"
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />

        <label htmlFor="register-password">
          Password
        </label>

        <input
          id="register-password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />

        {message && <p>{message}</p>}
        {error && <p>{error}</p>}

        <button type="submit">
          Register
        </button>

      </form>
    </div>
  );
}

export default Register;