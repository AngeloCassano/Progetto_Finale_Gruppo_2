import React from 'react';
import Element from './Element';
import useTier from '../hooks/useTier';
import './Tier.css';

const Tier = ({ tierLevel }) => {
  const { moveElementInTier, getOrderedElements } = useTier();
  
  // Ottieni gli elementi ordinati per questo tier
  const elementsInTier = getOrderedElements(tierLevel);

  const handleMoveLeft = (elementId) => {
    moveElementInTier(elementId, 'left');
  };

  const handleMoveRight = (elementId) => {
    moveElementInTier(elementId, 'right');
  };
  
  return (
    <div className="tier-container">
      <div className={`tier-header tier-${tierLevel.toLowerCase()}`}>
        Tier {tierLevel}
      </div>
      <div className="tier-elements">
        {elementsInTier.length > 0 ? (
          elementsInTier.map(elementId => (
            <Element 
              key={elementId}
              id={elementId}
              showButtons={false}
              showMoveButtons={true}
              onMoveLeft={handleMoveLeft}
              onMoveRight={handleMoveRight}
            />
          ))
        ) : (
          <div className="tier-empty">Nessun elemento in questo tier</div>
        )}
      </div>
    </div>
  );
};

export default Tier;