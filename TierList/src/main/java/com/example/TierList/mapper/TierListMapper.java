package com.example.TierList.mapper;

import com.example.TierList.dto.TierListDTO;
import com.example.TierList.model.Category;
import com.example.TierList.model.TierList;
import com.example.TierList.model.Utente;

public class TierListMapper {

    public static TierListDTO toDTO(TierList tierList) {
        if (tierList == null) return null;
        TierListDTO dto = new TierListDTO();
        dto.setId(tierList.getId());
        dto.setTitolo(tierList.getTitolo());
        dto.setUtenteId(tierList.getUtente().getId());
        dto.setUsername(tierList.getUtente().getUsername());
        dto.setCategoryId(tierList.getCategory().getId());
        dto.setCategoryName(tierList.getCategory().getCategoryName());
        return dto;
    }

    public static TierList toEntity(TierListDTO dto, Utente utente, Category category) {
        if (dto == null) return null;
        TierList tierList = new TierList();
        tierList.setId(dto.getId());
        tierList.setTitolo(dto.getTitolo());
        tierList.setUtente(utente);
        tierList.setCategory(category);
        return tierList;
    }
}
