import React from 'react';
import Tier from './Tier';
import './TierList.css';

const TierList = () => {
  const tiers = ['S', 'A', 'B', 'C', 'D'];
  
  return (
    <div className="tier-list">
      <h2 className="tier-list-title">Tier List</h2>
      <div className="tier-list-container">
        {tiers.map(tier => (
          <Tier key={tier} tierLevel={tier} />
        ))}
      </div>
    </div>
  );
};

export default TierList;