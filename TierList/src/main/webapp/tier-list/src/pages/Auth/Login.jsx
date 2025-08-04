// src/pages/Auth/Login.jsx
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth"; // Importa da hooks
import { authApi } from "../../services/api";
import "./Auth.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false); // Aggiungi stato loading
  const navigate = useNavigate();
  // Usa solo useAuth
  const { login, isAuthenticated } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    // Previeni submit multipli
    if (isLoading) return;
    
    setIsLoading(true);
    setError("");
    
    try {
      console.log('Tentativo di login...');
      const response = await authApi.login(username, password);
      console.log('Risposta API ricevuta:', response);
      
      // Assicurati che la risposta contenga il token
      if (!response.token) {
        throw new Error("Token non ricevuto dal server");
      }
      
      // Attendi che il login nel context sia completato
      await login(response.token);
      console.log('Context aggiornato, isAuthenticated:', isAuthenticated);
      
      // Navigazione con replace per evitare di tornare indietro
      navigate("/", { replace: true });
      
    } catch (err) {
      console.error("Errore durante il login:", err);
      setError(err.message || "Credenziali non valide");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h2>Login</h2>
        {error && <div className="auth-error">{error}</div>}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Username</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              disabled={isLoading}
            />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              disabled={isLoading}
            />
          </div>
          <button 
            type="submit" 
            className="auth-button"
            disabled={isLoading}
          >
            {isLoading ? "Accesso in corso..." : "Accedi"}
          </button>
        </form>
        <div className="auth-footer">
          Non hai un account? <a href="/register">Registrati</a>
        </div>
      </div>
    </div>
  );
};

export default Login;