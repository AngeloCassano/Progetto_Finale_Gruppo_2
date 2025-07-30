import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { authApi } from "../../services/api";
import "./Auth.css";

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await authApi.register(username, password, "USER");
      setRole("USER");
      console.log(role)
      navigate("/login");
    } catch (err) {
      setError("Registrazione fallita: " + err.message);
      console.log(err)
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
            Registrati
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
