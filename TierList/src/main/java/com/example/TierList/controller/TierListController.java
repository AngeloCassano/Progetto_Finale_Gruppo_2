package com.example.TierList.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TierList.dto.TierListDTO;
import com.example.TierList.mapper.TierListMapper;
import com.example.TierList.model.TierList;
import com.example.TierList.model.Utente;
import com.example.TierList.service.JwtService;
import com.example.TierList.service.TierListService;
import com.example.TierList.service.UtenteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tierlist")
@RequiredArgsConstructor
public class TierListController
{
    private final TierListService tierListService;
    private final JwtService jwtService;
    private final UtenteService utenteService;

    @PostMapping
    public ResponseEntity<TierListDTO> createTierList(
            @RequestBody TierListDTO dto,
            @RequestHeader("Authorization") String authHeader) {

        // ✅ Estrai token da "Bearer <token>"
        String token = authHeader.replace("Bearer ", "");

        // ✅ Decodifica JWT e prendi username
        String username = jwtService.extractUsername(token);

        // ✅ Trova utente dal DB
        Utente userCreator = utenteService.getUtenteByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        // ✅ Crea tier list
        TierList newList = tierListService.saveTierList(TierListMapper.toEntity(dto));

        return ResponseEntity.ok(TierListMapper.toDTO(newList));
    }
}