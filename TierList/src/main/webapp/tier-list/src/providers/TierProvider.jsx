/* eslint-disable no-prototype-builtins */
import React, { useState, useEffect } from 'react';
import TierContext from '../contexts/TierContext';
import useCategory from '../hooks/useCategory';

const TierProvider = ({ children }) => {
  const { selectedCategory } = useCategory();
  const [elements, setElements] = useState({});
  const [elementOrder, setElementOrder] = useState({});
  
  // Reset tier list when category changes
  useEffect(() => {
    resetTierList();
  }, [selectedCategory]);

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

  const addElementToTier = (elementId, tier, onElementUsed) => {
    setElementTier(elementId, tier);
    
    if (onElementUsed && typeof onElementUsed === 'function') {
      onElementUsed(elementId);
    }
  };

  const removeElementFromTier = (elementId, onElementFreed) => {
    resetElementToRooster(elementId);
    
    if (onElementFreed && typeof onElementFreed === 'function') {
      onElementFreed(elementId);
    }
  };

  const isElementInTierList = (elementId) => {
    return elements.hasOwnProperty(elementId);
  };

  const getAllUsedElements = () => {
    return Object.keys(elements);
  };

  const getTierStats = () => {
    const stats = {};
    const tiers = ['S', 'A', 'B', 'C', 'D'];
    
    tiers.forEach(tier => {
      stats[tier] = getOrderedElements(tier).length;
    });
    
    stats.total = Object.keys(elements).length;
    
    return stats;
  };

  const resetTierList = (onAllElementsFreed) => {
    const usedElementIds = Object.keys(elements);
    
    if (onAllElementsFreed && typeof onAllElementsFreed === 'function') {
      usedElementIds.forEach(elementId => {
        onAllElementsFreed(elementId);
      });
    }
    
    setElements({});
    setElementOrder({});
  };

  const exportTierList = () => {
    return {
      elements: { ...elements },
      elementOrder: { ...elementOrder },
      stats: getTierStats(),
      timestamp: new Date().toISOString()
    };
  };

  const importTierList = (tierListData, onElementsRestored) => {
    if (!tierListData || !tierListData.elements || !tierListData.elementOrder) {
      console.error('Dati tier list non validi');
      return false;
    }

    setElements(tierListData.elements);
    setElementOrder(tierListData.elementOrder);

    if (onElementsRestored && typeof onElementsRestored === 'function') {
      Object.keys(tierListData.elements).forEach(elementId => {
        onElementsRestored(elementId);
      });
    }

    return true;
  };

  const getTierListSummary = () => {
    const summary = {
      totalElements: Object.keys(elements).length,
      tierDistribution: getTierStats(),
      elementsByTier: {}
    };

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
      setElementTier, 
      getElementTier, 
      elements, 
      moveElementInTier,
      getOrderedElements,
      resetElementToRooster,
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