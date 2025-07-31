import React from 'react';
import Tier from './Tier';
import useTier from '../hooks/useTier';
import './TierList.css';

const TierList = () => {
  const tiers = ['S', 'A', 'B', 'C', 'D'];
  const { exportTierList } = useTier();
  
  const handleSave = async () => {
    const tierListData = exportTierList();
    try {
      // Qui dovrai implementare la chiamata API per salvare i dati
      console.log('Dati da salvare:', tierListData);
      alert('Tier List salvata con successo!');
    } catch (error) {
      console.error('Errore nel salvataggio:', error);
      alert('Errore nel salvataggio della Tier List');
    }
  };

  return (
    <div className="tier-list">
      <div className="tier-list-header">
        <h2 className="tier-list-title">Tier List</h2>
        <button 
          onClick={handleSave} 
          className="save-button"
        >
          Salva Tier List
        </button>
      </div>
      <div className="tier-list-container">
        {tiers.map(tier => (
          <Tier key={tier} tierLevel={tier} />
        ))}
      </div>
    </div>
  );
};

export default TierList;