package com.example.TierList.mapper;

import com.example.TierList.dto.UtenteDTO;
import com.example.TierList.model.Utente;

public class UtenteMapper {

    public static UtenteDTO toDTO(Utente utente) {
        if (utente == null) return null;
        return new UtenteDTO(utente.getId(), utente.getUsername(), utente.getRuolo());
    }

    public static Utente toEntity(UtenteDTO dto) {
        if (dto == null) return null;
        Utente utente = new Utente();
        utente.setId(dto.getId());
        utente.setUsername(dto.getUsername());
        utente.setRuolo(dto.getRuolo());
        return utente;
    }
}