package com.example.TierList.repository;

import com.example.TierList.model.TierUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<TierUser, Long> {
    // Trova un utente per username
    Optional<TierUser> findByUsername(String username);

    // Verifica se un username esiste già (utile per la registrazione)
    boolean existsByUsername(String username);
}