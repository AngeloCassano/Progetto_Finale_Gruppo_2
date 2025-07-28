package com.example.TierList.mapper;

import com.example.TierList.dto.UtenteDTO;
import com.example.TierList.model.TierUser;

public class UtenteMapper {

    public static UtenteDTO toDTO(TierUser utente) {
        if (utente == null) return null;
        return new UtenteDTO(utente.getId(), utente.getUsername(), utente.getRole());
    }

    public static TierUser toEntity(UtenteDTO dto) {
        if (dto == null) return null;
        TierUser utente = new TierUser();
        utente.setId(dto.getId());
        utente.setUsername(dto.getUsername());
        utente.setRole(dto.getRuolo());
        return utente;
    }
}