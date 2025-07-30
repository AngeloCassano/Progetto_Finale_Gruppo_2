// src/contexts/Auth/useAuth.jsx
import { useContext } from "react";
import AuthContext from "./AuthContext";

const useAuth = () => {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error("useAuth deve essere usato dentro un AuthProvider.");
  }

  return context;
};

export default useAuth;
