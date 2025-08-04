// src/components/Layout/Layout.jsx
import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth'; // Importa da hooks
import './Layout.css';

const Layout = ({ children }) => {
  const { isAuthenticated, logout, loading, user } = useAuth();

  // Mostra un loading spinner mentre il context si inizializza
  if (loading) {
    return (
      <div className="layout">
        <div className="loading">Caricamento...</div>
      </div>
    );
  }

  const handleLogout = () => {
    logout();
    // Opzionale: redirect alla home o alla pagina di login
    // navigate('/login');
  };

  return (
    <div className="layout">
      <header className="header">
        <div className="container">
          <Link to="/" className="logo">
            Tier List System
          </Link>
          <nav className="nav">
            {isAuthenticated ? (
              <div className="auth-section">
                {user && <span className="user-greeting">Ciao, {user.username}!</span>}
                <button onClick={handleLogout} className="logout-button">
                  Logout
                </button>
              </div>
            ) : (
              <div className="auth-links">
                <Link to="/login" className="auth-link">Login</Link>
                <Link to="/register" className="auth-link">Register</Link>
              </div>
            )}
          </nav>
        </div>
      </header>
      <main className="main">
        {children}
      </main>
    </div>
  );
};

export default Layout;