// src/contexts/AuthContext.jsx
import { createContext } from 'react';

// Crea il context con valore di default
const AuthContext = createContext({
  user: null,
  token: null,
  isAuthenticated: false,
  loading: true,
  login: async () => {},
  logout: () => {}
});

export default AuthContext;