import { useContext } from 'react';
import TierContext from '../contexts/TierContext';

// Hook personalizzato per usare il Context
const useTier = () => {
  const context = useContext(TierContext);
  if (!context) {
    throw new Error('useTier deve essere usato all\'interno di TierProvider');
  }
  return context;
};

export default useTier;