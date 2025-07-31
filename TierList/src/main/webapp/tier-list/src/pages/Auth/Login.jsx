// src/pages/Auth/Login.jsx
import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import  AuthContext  from "../../contexts/AuthContext";
import { authApi } from "../../services/api";
import "./Auth.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await authApi.login(username, password);
      login(response.accessToken); // Usa il metodo login dal context
      navigate("/");
    } catch (err) {
      console.log(err);
      setError("Credenziali non valide");
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
            />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="auth-button">
            Accedi
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
