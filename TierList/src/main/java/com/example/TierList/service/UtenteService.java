package com.example.TierList.service;

import com.example.TierList.model.Utente;
import com.example.TierList.repository.UtenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service 
public class UtenteService {

    private final UtenteRepository utenteRepository;

 
    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Optional<Utente> getUtenteById(Long id) {
        return utenteRepository.findById(id);
    }


    public Optional<Utente> getUtenteByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }


    public Utente saveUtente(Utente utente) {

        return utenteRepository.save(utente);
    }


    public void deleteUtente(Long id) {
        utenteRepository.deleteById(id);
    }

    
    public boolean existsByUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }
}
