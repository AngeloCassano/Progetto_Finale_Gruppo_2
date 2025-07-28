package com.example.TierList.service;

import com.example.TierList.model.TierUser;
import com.example.TierList.repository.UtenteRepository;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtenteRepository repo;

    public CustomUserDetailsService(UtenteRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TierUser u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRole().toString()) // es. "USER"
                .build();
    }
}