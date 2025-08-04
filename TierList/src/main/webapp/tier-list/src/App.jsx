// src/App.jsx
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import Layout from "./components/Layout/Layout";
import Login from "./pages/Auth/Login";
import Register from "./pages/Auth/Register";
import Home from "./pages/Home/Home";
import AuthProvider from "./providers/AuthProvider";
import TierProvider from "./providers/TierProvider";
import CategoryProvider from "./providers/CategoryProvider";

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <TierProvider>
          <CategoryProvider>
            <Layout>
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                {/* Esempio di rotta protetta */}
              </Routes>
            </Layout>
          </CategoryProvider>
        </TierProvider>
      </Router>
    </AuthProvider>
  );
};

export default App;
