import React from 'react';
import useTier from '../hooks/useTier';
import './Element.css';

const Element = ({ id, imageUrl, showButtons = true, showMoveButtons = false, onMoveLeft, onMoveRight }) => {
  const { setElementTier, getElementTier, resetElementToRooster } = useTier();
  const currentTier = getElementTier(id);
  
  const tiers = ['S', 'A', 'B', 'C', 'D'];
  
  const handleTierSelect = (tier) => {
    setElementTier(id, tier);
  };

  const handleMoveLeft = () => {
    if (onMoveLeft) onMoveLeft(id);
  };

  const handleMoveRight = () => {
    if (onMoveRight) onMoveRight(id);
  };

  const handleReset = () => {
    resetElementToRooster(id);
  };
  
  return (
    <div className="element-wrapper">
      {/* Bottone sposta sinistra */}
      {showMoveButtons && (
        <button className="move-button move-left" onClick={handleMoveLeft}>
          ◀
        </button>
      )}
      
      <div className="element-container">
        {/* Immagine quadrata */}
        <img 
          src={imageUrl || '/api/placeholder/96/96'} 
          alt={`Element ${id}`}
          className="element-image"
        />
        
        {/* Tier badge (sempre visibile se assegnato) */}
        {currentTier && (
          <div className={`tier-badge tier-${currentTier.toLowerCase()}`}>
            {currentTier}
          </div>
        )}

        {/* Bottone reset (visibile solo se l'elemento è in un tier) */}
        {currentTier && (
          <button className="reset-button" onClick={handleReset} title="Rimuovi dal tier">
            ✕
          </button>
        )}
        
        {/* Bottoni per selezione tier (visibili solo se showButtons è true) */}
        {showButtons && (
          <div className="tier-buttons-overlay">
            {tiers.map(tier => (
              <button
                key={tier}
                onClick={() => handleTierSelect(tier)}
                className={`tier-button tier-${tier.toLowerCase()}`}
              >
                {tier}
              </button>
            ))}
          </div>
        )}
      </div>

      {/* Bottone sposta destra */}
      {showMoveButtons && (
        <button className="move-button move-right" onClick={handleMoveRight}>
          ▶
        </button>
      )}
    </div>
  );
};

export default Element;