import React, { useState } from 'react';
import Tier from './Tier';
import useTier from '../hooks/useTier';
import useAuth from '../hooks/useAuth';
import useCategory from '../hooks/useCategory';
import { tierListApi } from '../services/api';
import './TierList.css';

const TierList = () => {
  const tiers = ['S', 'A', 'B', 'C', 'D'];
  const { exportTierList } = useTier();
  const { token, isAuthenticated } = useAuth();
  const { selectedCategory } = useCategory();
  
  const [isSaving, setIsSaving] = useState(false);
  const [saveSuccess, setSaveSuccess] = useState(false);
  const [error, setError] = useState(null);

  const handleSave = async () => {
    setError(null);
    setIsSaving(true);
    
    try {
      if (!isAuthenticated) throw new Error('Devi effettuare il login per salvare');
      if (!selectedCategory) throw new Error('Seleziona una categoria prima di salvare');

      const dataToSave = {
        title: `Tier List - ${selectedCategory.name}`,
        categoryId: selectedCategory.id,
        tiers: exportTierList().elementOrder,
        elements: exportTierList().elements,
        createdAt: new Date().toISOString()
      };

      await tierListApi.createTierList(dataToSave, token);
      
      setSaveSuccess(true);
      setTimeout(() => setSaveSuccess(false), 3000);
      
    } catch (err) {
      console.error('Errore nel salvataggio:', err);
      setError(err.message.includes('403') 
        ? 'Accesso negato. Verifica di essere loggato' 
        : err.message || 'Errore durante il salvataggio');
    } finally {
      setIsSaving(false);
    }
  };

  return (
    <div className="tier-list">
      <div className="tier-list-header">
        <h2 className="tier-list-title">Tier List</h2>
        
        <div className="save-controls">
          <button 
            onClick={handleSave}
            className={`save-button ${isSaving ? 'saving' : ''} ${!isAuthenticated ? 'disabled' : ''}`}
            disabled={isSaving || !isAuthenticated}
          >
            {isSaving ? (
              <>
                <span className="spinner"></span>
                Salvataggio...
              </>
            ) : (
              'Salva Tier List'
            )}
          </button>

          {saveSuccess && (
            <div className="save-feedback success">
              <span>✓</span> Salvato con successo!
            </div>
          )}

          {error && (
            <div className="save-feedback error">
              <span>⚠</span> {error}
            </div>
          )}
        </div>
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