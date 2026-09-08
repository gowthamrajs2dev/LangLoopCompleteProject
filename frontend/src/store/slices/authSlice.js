import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

const initialState = {
  user: null,
  isAuthenticated: false,
  loading: false,
  error: null
};

export const login = createAsyncThunk(
  'auth/login',
  async ({ username, password }, { rejectWithValue }) => {
    try {
      const response = await import('../../services/api').then(
        (module) => module.default.post('/auth/login', {
          username,
          password
        })
      );

      const user = response.data;

      localStorage.setItem(
        'langloop_token',
        user.token || 'jwt'
      );

      localStorage.setItem(
        'langloop_role',
        user.role || 'LEARNER'
      );

      return user;
    } catch (error) {
      return rejectWithValue(
        error?.response?.data?.message || 'Login failed'
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
      });
  }
});

export const { logout } = authSlice.actions;

export default authSlice.reducer;