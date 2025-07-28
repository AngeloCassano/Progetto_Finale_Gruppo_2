package com.example.TierList.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TierList.dto.UtenteDTO;
import com.example.TierList.mapper.UtenteMapper;
import com.example.TierList.model.Utente;
import com.example.TierList.repository.UtenteRepository;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping
    public List<UtenteDTO> getAll() {
        return utenteRepository.findAll()
                .stream()
                .map(UtenteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getById(@PathVariable Long id) {
        return utenteRepository.findById(id)
                .map(u -> ResponseEntity.ok(UtenteMapper.toDTO(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UtenteDTO create(@RequestBody UtenteDTO dto) {
        Utente utente = UtenteMapper.toEntity(dto);
        Utente saved = utenteRepository.save(utente);
        return UtenteMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtenteDTO> update(@PathVariable Long id, @RequestBody UtenteDTO dto) {
        return utenteRepository.findById(id)
                .map(existing -> {
                    existing.setUsername(dto.getUsername());
                    existing.setRuolo(dto.getRuolo());
                    Utente updated = utenteRepository.save(existing);
                    return ResponseEntity.ok(UtenteMapper.toDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return utenteRepository.findById(id)
                .map(existing -> {
                    utenteRepository.delete(existing);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
