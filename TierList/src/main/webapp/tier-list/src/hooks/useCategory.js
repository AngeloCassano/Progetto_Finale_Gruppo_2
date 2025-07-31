// useCategory.js (Hook personalizzato)
import { useContext } from 'react';
import CategoryContext from '../contexts/CategoryContext';

const useCategory = () => {
  const context = useContext(CategoryContext);
  if (!context) {
    throw new Error('useCategory deve essere usato all\'interno di CategoryProvider');
  }
  return context;
};

export default useCategory;