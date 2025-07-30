import React from 'react';
import TierProvider from './providers/TierProvider';
import ElementRooster from './components/ElementRooster';
import TierList from './components/TierList';
import './App.css';

const App = () => {
  return (
    <TierProvider>
      <div className="app-container">
        <h1 className="app-title">Tier List System</h1>
        
        {/* Element Rooster */}
        <ElementRooster />
        
        {/* Tier List */}
        <TierList />
      </div>
    </TierProvider>
  );
};

export default App;