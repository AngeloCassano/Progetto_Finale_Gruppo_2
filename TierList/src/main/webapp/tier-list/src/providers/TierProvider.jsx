import React, { useState } from 'react';
import TierContext from '../contexts/TierContext';

// Provider del Context
const TierProvider = ({ children }) => {
  const [elements, setElements] = useState({});
  const [elementOrder, setElementOrder] = useState({});
  
  const setElementTier = (elementId, newTier) => {
    const currentTier = elements[elementId];
    
    // Rimuovi l'elemento dal tier precedente se esiste
    if (currentTier && currentTier !== newTier) {
      setElementOrder(prev => ({
        ...prev,
        [currentTier]: prev[currentTier] ? prev[currentTier].filter(id => id !== elementId) : []
      }));
    }
    
    // Aggiorna il tier dell'elemento
    setElements(prev => ({
      ...prev,
      [elementId]: newTier
    }));
    
    // Aggiungi l'elemento al nuovo tier se non è già presente
    setElementOrder(prev => {
      if (!prev[newTier]) {
        return {
          ...prev,
          [newTier]: [elementId]
        };
      }
      if (!prev[newTier].includes(elementId)) {
        return {
          ...prev,
          [newTier]: [...prev[newTier], elementId]
        };
      }
      return prev;
    });
  };

  const resetElementToRooster = (elementId) => {
    const currentTier = elements[elementId];
    
    // Rimuovi l'elemento dal tier corrente
    if (currentTier) {
      setElementOrder(prev => ({
        ...prev,
        [currentTier]: prev[currentTier] ? prev[currentTier].filter(id => id !== elementId) : []
      }));
    }
    
    // Rimuovi l'elemento dalla mappa degli elementi (resetta a null)
    setElements(prev => {
      const newElements = { ...prev };
      delete newElements[elementId];
      return newElements;
    });
  };
  
  const getElementTier = (elementId) => {
    return elements[elementId] || null;
  };

  const moveElementInTier = (elementId, direction) => {
    const tier = elements[elementId];
    if (!tier || !elementOrder[tier]) return;

    setElementOrder(prev => {
      const tierElements = [...prev[tier]];
      const currentIndex = tierElements.indexOf(elementId);
      
      if (currentIndex === -1) return prev;

      let newIndex;
      if (direction === 'left') {
        newIndex = Math.max(0, currentIndex - 1);
      } else {
        newIndex = Math.min(tierElements.length - 1, currentIndex + 1);
      }

      // Scambia gli elementi
      [tierElements[currentIndex], tierElements[newIndex]] = [tierElements[newIndex], tierElements[currentIndex]];

      return {
        ...prev,
        [tier]: tierElements
      };
    });
  };

  const getOrderedElements = (tier) => {
    return elementOrder[tier] || [];
  };
  
  return (
    <TierContext.Provider value={{ 
      setElementTier, 
      getElementTier, 
      elements, 
      moveElementInTier,
      getOrderedElements,
      resetElementToRooster
    }}>
      {children}
    </TierContext.Provider>
  );
};

export default TierProvider;