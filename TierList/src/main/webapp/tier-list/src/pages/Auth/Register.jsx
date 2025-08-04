import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { authApi } from "../../services/api";
import "./Auth.css";

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError("");

    try {
      const response = await authApi.register(username, password, "USER");

      // Check if response exists and is successful
      if (response.status === 'success') {
        navigate("/login");
      } else {
        console.log(response);
        console.log(response.status);
        throw new Error(
          "Registrazione completata ma risposta inattesa dal server"
        );
      }
    } catch (err) {
      // Handle different error types
      const errorMsg =
        err.response?.data?.message ||
        err.message ||
        "Errore durante la registrazione";
      setError(errorMsg);
      console.error("Registration error:", err);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h2>Registrazione</h2>
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
          <button type="submit" className="auth-button" disabled={isLoading}>
            {isLoading ? "Registrazione in corso..." : "Registrati"}
          </button>
        </form>
        <div className="auth-footer">
          Hai già un account? <a href="/login">Accedi</a>
        </div>
      </div>
    </div>
  );
};

export default Register;
