package com.example.TierList.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ResponseEntity<UtenteDTO> getUserInfo(Authentication auth) {
            return utenteRepository.findByUsername(auth.getName())
                .map(u -> ResponseEntity.ok(UtenteMapper.toDTO(u)))
                .orElse(ResponseEntity.notFound().build());
         
    }

    @PutMapping
    public ResponseEntity<UtenteDTO> update(Authentication auth, @RequestBody UtenteDTO dto) {
        return utenteRepository.findByUsername(auth.getName())
                .map(existing -> {
                    existing.setUsername(dto.getUsername());
                    Utente updated = utenteRepository.save(existing);
                    return ResponseEntity.ok(UtenteMapper.toDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Authentication auth) {
        return utenteRepository.findByUsername(auth.getName())
                .map(existing -> {
                    utenteRepository.delete(existing);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/info")
    public String infoUtente(Authentication auth) {
        return "Ciao " + auth.getName() + ", sei autenticato come USER o ADMIN.";
    }
}
