/* eslint-disable no-prototype-builtins */
import React, { useState } from 'react';
import TierContext from '../contexts/TierContext';

// Provider del Context con gestione elementi unici
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
    
    // Rimuovi l'elemento dalla mappa degli elementi
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

  // Nuove funzioni per gestire elementi unici
  const addElementToTier = (elementId, tier, onElementUsed) => {
    setElementTier(elementId, tier);
    
    // Callback opzionale per notificare che l'elemento è stato usato
    if (onElementUsed && typeof onElementUsed === 'function') {
      onElementUsed(elementId);
    }
  };

  const removeElementFromTier = (elementId, onElementFreed) => {
    resetElementToRooster(elementId);
    
    // Callback opzionale per notificare che l'elemento è stato liberato
    if (onElementFreed && typeof onElementFreed === 'function') {
      onElementFreed(elementId);
    }
  };

  // Verifica se un elemento è già stato utilizzato in una tier list
  const isElementInTierList = (elementId) => {
    return elements.hasOwnProperty(elementId);
  };

  // Ottieni tutti gli elementi utilizzati
  const getAllUsedElements = () => {
    return Object.keys(elements);
  };

  // Ottieni statistiche delle tier
  const getTierStats = () => {
    const stats = {};
    const tiers = ['S', 'A', 'B', 'C', 'D'];
    
    tiers.forEach(tier => {
      stats[tier] = getOrderedElements(tier).length;
    });
    
    stats.total = Object.keys(elements).length;
    
    return stats;
  };

  // Reset completo della tier list (utile quando si cambia categoria)
  const resetTierList = (onAllElementsFreed) => {
    const usedElementIds = Object.keys(elements);
    
    // Callback per notificare che tutti gli elementi sono stati liberati
    if (onAllElementsFreed && typeof onAllElementsFreed === 'function') {
      usedElementIds.forEach(elementId => {
        onAllElementsFreed(elementId);
      });
    }
    
    setElements({});
    setElementOrder({});
  };

  // Esporta la tier list corrente (per salvataggio)
  const exportTierList = () => {
    return {
      elements: { ...elements },
      elementOrder: { ...elementOrder },
      stats: getTierStats(),
      timestamp: new Date().toISOString()
    };
  };

  // Importa una tier list (per caricamento)
  const importTierList = (tierListData, onElementsRestored) => {
    if (!tierListData || !tierListData.elements || !tierListData.elementOrder) {
      console.error('Dati tier list non validi');
      return false;
    }

    setElements(tierListData.elements);
    setElementOrder(tierListData.elementOrder);

    // Callback per notificare il ripristino degli elementi
    if (onElementsRestored && typeof onElementsRestored === 'function') {
      Object.keys(tierListData.elements).forEach(elementId => {
        onElementsRestored(elementId);
      });
    }

    return true;
  };

  // Funzione per ottenere un riepilogo completo
  const getTierListSummary = () => {
    const summary = {
      totalElements: Object.keys(elements).length,
      tierDistribution: getTierStats(),
      elementsByTier: {}
    };

    // Raggruppa elementi per tier
    Object.entries(elements).forEach(([elementId, tier]) => {
      if (!summary.elementsByTier[tier]) {
        summary.elementsByTier[tier] = [];
      }
      summary.elementsByTier[tier].push(elementId);
    });

    return summary;
  };
  
  return (
    <TierContext.Provider value={{ 
      // Funzioni originali
      setElementTier, 
      getElementTier, 
      elements, 
      moveElementInTier,
      getOrderedElements,
      resetElementToRooster,
      
      // Nuove funzioni per gestione elementi unici
      addElementToTier,
      removeElementFromTier,
      isElementInTierList,
      getAllUsedElements,
      getTierStats,
      resetTierList,
      exportTierList,
      importTierList,
      getTierListSummary
    }}>
      {children}
    </TierContext.Provider>
  );
};

export default TierProvider;