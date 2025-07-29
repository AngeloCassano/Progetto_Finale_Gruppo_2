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

//Controller REST per la gestione degli utenti
@RestController
@RequestMapping("/user")
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepository;

<<<<<<< HEAD


    //Endpoint Put che aggiorna un utente
=======
>>>>>>> df102c4e8438571f12f8b3252f4ddff776700903
    @PutMapping("/{id}")
    public ResponseEntity<UtenteDTO> update(@PathVariable Long id, @RequestBody UtenteDTO dto) {
        return utenteRepository.findById(id)
                .map(existing -> {
                    existing.setUsername(dto.getUsername());
                    Utente updated = utenteRepository.save(existing);
                    return ResponseEntity.ok(UtenteMapper.toDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //Endpoint Delete che cancella un utente tramite id 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return utenteRepository.findById(id)
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
