package com.example.TierList.service;

import com.example.TierList.model.Utente;
import com.example.TierList.repository.UtenteRepository;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {    // Implementa l'interfaccia UserDetailsService di Spring Security

    private final UtenteRepository repo;    // Dichiarazione del repository per interagire con il database degli utenti


    public CustomUserDetailsService(UtenteRepository repo) {   // Costruttore che inietta l'UtenteRepository
        this.repo = repo;
    }

    @Override  // Sovrascrive il metodo dell'interfaccia UserDetailsService
    // Metodo per caricare i dettagli dell'utente tramite username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente u = repo.findByUsername(username) // Cerca l'utente nel database tramite username
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        return User.builder()                     // Costruisce un oggetto UserDetails (Spring Security)
                .username(u.getUsername())        // Imposta lo username
                .password(u.getPassword())        // imposta la password
                .roles(u.getRuolo().toString()) 
                .build();
    }
}