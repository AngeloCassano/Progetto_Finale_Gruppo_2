// src/contexts/Auth/AuthProvider.jsx
import { useState } from "react";
import AuthContext from "../contexts/AuthContext";

const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [user, setUser] = useState(null);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const login = (userData) => {
    setUser(userData);
    setIsAuthenticated(true);
    if (isAuthenticated) {
      setIsLoggedIn(true);
    }
  };

  const logout = () => {
    setUser(null);
    setIsAuthenticated(false);
    setIsLoggedIn(false);
  };

  const contextValue = {
    isAuthenticated,
    isLoggedIn,
    user,
    login,
    logout,
  };

  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
};

export default AuthProvider;
