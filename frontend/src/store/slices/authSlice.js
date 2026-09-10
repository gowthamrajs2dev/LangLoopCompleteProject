import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import api from '../../services/api';

const storedToken = localStorage.getItem('langloop_token');
const storedRole = localStorage.getItem('langloop_role');
const storedUsername = localStorage.getItem('langloop_username');

const initialState = {
  user: storedToken
    ? { token: storedToken, role: storedRole, username: storedUsername }
    : null,
  isAuthenticated: Boolean(storedToken),
  loading: false,
  error: null
};

const persistSession = (user) => {
  localStorage.setItem('langloop_token', user.token || '');
  localStorage.setItem('langloop_role', user.role || 'LEARNER');
  localStorage.setItem('langloop_username', user.username || '');
};

export const login = createAsyncThunk(
  'auth/login',
  async ({ username, password }, { rejectWithValue }) => {
    try {
      const response = await api.post('/auth/login', { username, password });
      const user = response.data;
      persistSession(user);
      return user;
    } catch (error) {
      return rejectWithValue(
        error?.response?.data?.message || 'Login failed'
      );
    }
  }
);

export const register = createAsyncThunk(
  'auth/register',
  async ({ username, password, role }, { rejectWithValue }) => {
    try {
      const response = await api.post('/auth/register', {
        username,
        password,
        role
      });
      const user = response.data;
      persistSession(user);
      return user;
    } catch (error) {
      return rejectWithValue(
        error?.response?.data?.message || 'Registration failed'
      );
    }
  }
);

const authSlice = createSlice({
  name: 'auth',

  initialState,

  reducers: {
    logout: (state) => {
      state.user = null;
      state.isAuthenticated = false;
      state.loading = false;
      state.error = null;

      localStorage.removeItem('langloop_token');
      localStorage.removeItem('langloop_role');
      localStorage.removeItem('langloop_username');
    },
    clearAuthError: (state) => {
      state.error = null;
    }
  },

  extraReducers: (builder) => {
    builder
      .addCase(login.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(login.fulfilled, (state, action) => {
        state.loading = false;
        state.isAuthenticated = true;
        state.user = action.payload;
      })
      .addCase(login.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload || 'Login failed';
      })
      .addCase(register.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(register.fulfilled, (state, action) => {
        state.loading = false;
        state.isAuthenticated = true;
        state.user = action.payload;
      })
      .addCase(register.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload || 'Registration failed';
      });
  }
});

export const { logout, clearAuthError } = authSlice.actions;

export default authSlice.reducer;
