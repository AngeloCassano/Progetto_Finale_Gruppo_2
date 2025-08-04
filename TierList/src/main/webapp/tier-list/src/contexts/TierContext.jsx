import { createContext } from 'react';

const TierContext = createContext({
  tiers: {},
  moveElementInTier: () => {},
  getOrderedElements: () => {},
  resetTiers: () => {}, // Add reset functionality
  exportTierList: () => {},
});

export default TierContext;