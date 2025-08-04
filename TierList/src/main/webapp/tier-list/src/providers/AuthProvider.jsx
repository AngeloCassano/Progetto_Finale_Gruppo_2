// src/contexts/AuthProvider.jsx
import React, { useState, useEffect } from 'react';
import AuthContext from '../contexts/AuthContext';

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [loading, setLoading] = useState(true);

  // Inizializza il context dal localStorage al mount
  useEffect(() => {
    console.log('AuthProvider: Inizializzazione...');
    const savedToken = localStorage.getItem('accessToken');
    if (savedToken) {
      console.log('AuthProvider: Token trovato nel localStorage');
      setToken(savedToken);
      setIsAuthenticated(true);
      // Opzionale: qui potresti fare una chiamata API per validare il token
    }
    setLoading(false);
    console.log('AuthProvider: Inizializzazione completata');
  }, []);

  // Funzione di login
  const login = async (accessToken) => {
    try {
      console.log('AuthProvider: Effettuando login con token:', accessToken);
      
      // Salva il token nel localStorage
      localStorage.setItem('accessToken', accessToken);
      
      // Aggiorna lo stato
      setToken(accessToken);
      setIsAuthenticated(true);
      
      console.log('AuthProvider: Login completato, isAuthenticated:', true);
      
      return Promise.resolve();
    } catch (error) {
      console.error('AuthProvider: Errore durante il login:', error);
      return Promise.reject(error);
    }
  };

  // Funzione di logout
  const logout = () => {
    console.log('AuthProvider: Effettuando logout...');
    localStorage.removeItem('accessToken');
    setToken(null);
    setUser(null);
    setIsAuthenticated(false);
    console.log('AuthProvider: Logout completato');
  };

  // Il valore che verrà fornito ai componenti figli
  const contextValue = {
    user,
    token,
    isAuthenticated,
    loading,
    login,
    logout
  };

  console.log('AuthProvider: Rendering con valore:', contextValue);

  return (
    <AuthContext.Provider value={contextValue}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;