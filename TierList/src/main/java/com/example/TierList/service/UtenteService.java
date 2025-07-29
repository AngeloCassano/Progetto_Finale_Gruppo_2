package com.example.TierList.service;

import com.example.TierList.model.Utente;
import com.example.TierList.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder; // Potrebbe servire per l'hashing della password
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder; 

    /**
      Recupera tutti gli utenti.
      @return Una lista di tutti gli Utente.
     */
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    /**
      Recupera un utente tramite il suo ID.
      @param id L'ID dell'utente.
      @return Un Optional contenente l'Utente se trovato.
     */
    public Optional<Utente> getUtenteById(Long id) {
        return utenteRepository.findById(id);
    }

    /**
      Recupera un utente tramite il suo username.
      @param username Lo username dell'utente.
      @return Un Optional contenente l'Utente se trovato.
     */
    public Optional<Utente> getUtenteByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    /**
      Salva un nuovo utente o aggiorna uno esistente.
      Hashing della password per sicurezza.
      @param utente L'oggetto Utente da salvare/aggiornare.
      @return L'Utente salvato/aggiornato.
     */
    public Utente saveUtente(Utente utente) {
        // Hashing della password PRIMA di salvarla
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    
    // Elimina un utente tramite il suo ID.
    
    public void deleteUtente(Long id) {
        utenteRepository.deleteById(id);
    }

    
    // Verifica se un username esiste già.
     
    public boolean existsUtenteByUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }
}