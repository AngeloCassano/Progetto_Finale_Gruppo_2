// src/pages/Home/Home.jsx
import React from "react";
import ElementRooster from "../../components/ElementRooster";
import TierList from "../../components/TierList";
import "../../App.css";
import { useContext } from "react";
import AuthContext from "../../contexts/AuthContext";
import Login from "../Auth/Login";
import CategorySelector from "../../components/CategorySelector";

const Home = () => {
  const { isAuthenticated, isLoggedIn } = useContext(AuthContext);
  return (
    <div className="app-container">
      {isAuthenticated && isLoggedIn ? (
        <>
          <CategorySelector />
          <ElementRooster />
          <TierList />
        </>
      ) : (
        <Login />
      )}
    </div>
  );
};

export default Home;
