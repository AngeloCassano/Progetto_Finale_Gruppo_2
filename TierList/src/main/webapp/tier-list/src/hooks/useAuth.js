// src/hooks/useAuth.js
import { useContext } from 'react';
import AuthContext from '../contexts/AuthContext';

export const useAuth = () => {
  const context = useContext(AuthContext);
  
  console.log('useAuth: Context ricevuto:', context);
  
  if (context === undefined) {
    throw new Error('useAuth deve essere usato all\'interno di un AuthProvider');
  }
  
  // Verifica aggiuntiva per essere sicuri che il context abbia i valori corretti
  if (context.login === undefined || context.logout === undefined) {
    throw new Error('AuthContext non è stato inizializzato correttamente');
  }
  
  return context;
};

export default useAuth;