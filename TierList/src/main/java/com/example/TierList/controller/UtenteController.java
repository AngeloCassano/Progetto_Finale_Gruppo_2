package com.example.TierList.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TierList.dto.UtenteDTO;
import com.example.TierList.mapper.UtenteMapper;
import com.example.TierList.model.Utente;
import com.example.TierList.repository.UtenteRepository;

@RestController
@RequestMapping("/user")
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepository;

    @PutMapping("/update")
    public ResponseEntity<UtenteDTO> update(@RequestBody UtenteDTO dto, Authentication auth) {
        return utenteRepository.findByUsername(auth.getName())
                .stream()
                .peek(utente -> {
                    // Aggiorna i campi dell'utente esistente con i dati dal DTO
                    utente.setUsername(dto.getUsername());
                    // Aggiungi altri campi secondo necessità
                })
                .map(utenteRepository::save)
                .map(UtenteMapper::toDTO) // Converti a DTO
                .map(ResponseEntity::ok)
                .findFirst()
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(Authentication auth) {
        Optional<Utente> utenteOpt = utenteRepository.findByUsername(auth.getName());

        if (utenteOpt.isPresent()) {
            utenteRepository.delete(utenteOpt.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/info")
    public String infoUtente(Authentication auth) {
        return "Ciao " + auth.getName() + ", sei autenticato come USER o ADMIN.";
    }
}
