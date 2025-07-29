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
    private final PasswordEncoder passwordEncoder; // Richiede Spring Security

    // Se non usi Spring Security per l'hashing, puoi rimuovere il PasswordEncoder
    // e il suo uso nel costruttore e nel metodo saveUtente.
    // CONSIGLIO VIVAMENTE DI USARLO PER LA SICUREZZA DELLE PASSWORD.
    // Se non lo hai ancora aggiunto al pom.xml, fallo:
    /*
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    */
    // E definisci il bean PasswordEncoder in una classe @Configuration.


    /**
     * Recupera tutti gli utenti.
     * @return Una lista di tutti gli Utente.
     */
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    /**
     * Recupera un utente tramite il suo ID.
     * @param id L'ID dell'utente.
     * @return Un Optional contenente l'Utente se trovato.
     */
    public Optional<Utente> getUtenteById(Long id) {
        return utenteRepository.findById(id);
    }

    /**
     * Recupera un utente tramite il suo username.
     * @param username Lo username dell'utente.
     * @return Un Optional contenente l'Utente se trovato.
     */
    public Optional<Utente> getUtenteByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    /**
     * Salva un nuovo utente o aggiorna uno esistente.
     * Hashing della password per sicurezza.
     * @param utente L'oggetto Utente da salvare/aggiornare.
     * @return L'Utente salvato/aggiornato.
     */
    public Utente saveUtente(Utente utente) {
        // Hashing della password PRIMA di salvarla
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    /**
     * Elimina un utente tramite il suo ID.
     * @param id L'ID dell'utente da eliminare.
     */
    public void deleteUtente(Long id) {
        utenteRepository.deleteById(id);
    }

    /**
     * Verifica se un username esiste già.
     * @param username Lo username da controllare.
     * @return true se l'username esiste, false altrimenti.
     */
    public boolean existsUtenteByUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }
}