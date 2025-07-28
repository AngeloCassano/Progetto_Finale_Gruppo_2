package com.example.TierList.repository;

import com.example.TierList.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    // Trova un utente tramite il suo username
    Optional<Utente> findByUsername(String username);

    // Controlla se un username esiste già nel database
    boolean existsByUsername(String username);
}
