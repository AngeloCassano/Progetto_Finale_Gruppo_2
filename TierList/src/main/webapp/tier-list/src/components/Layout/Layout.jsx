// src/components/Layout/Layout.jsx
import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import AuthContext from '../../contexts/AuthContext';
import './Layout.css';

const Layout = ({ children }) => {
  const { isAuthenticated, isLoggedIn, logout } = useContext(AuthContext);

  return (
    <div className="layout">
      <header className="header">
        <div className="container">
          <Link to="/" className="logo">
            Tier List System
          </Link>
          <nav className="nav">
            {isAuthenticated && isLoggedIn ? (
              <button onClick={logout} className="logout-button">
                Logout
              </button>
            ) : (
              <>
                <Link to="/login">Login</Link>
                <Link to="/register">Register</Link>
              </>
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