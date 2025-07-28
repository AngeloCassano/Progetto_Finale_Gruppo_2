package com.example.TierList.mapper;

import com.example.TierList.dto.TierListDTO;
import com.example.TierList.model.Category;
import com.example.TierList.model.TierList;
import com.example.TierList.model.TierUser;

public class TierListMapper
{
    public static TierListDTO toDTO(TierList tierList) {
        if (tierList == null) return null;
        TierListDTO dto = new TierListDTO();
        dto.setId(tierList.getId());
        dto.setTitolo(tierList.getTitolo());
        dto.setUtenteId(tierList.getUtente().getId());
        dto.setUsername(tierList.getUtente().getUsername());
        dto.setCategoryId(tierList.getTierElement().getId());
        dto.setCategoryName(tierList.getTierElement().getName());
        return dto;
    }

    public static TierList toEntity(TierListDTO dto)
    {
        if (dto == null) return null;

        TierList tierList = new TierList();

        TierUser utente = null;

        if (dto.getUtenteId() != null) {
            utente = new TierUser();
            utente.setId(dto.getUtenteId());
        }

        Category category = null;
        
        if (dto.getCategoryId() != null) {
            category = new Category();
            category.setId(dto.getCategoryId());
        }

        tierList.setId(dto.getId());
        tierList.setTitle(dto.getTitolo());
        tierList.setUser(utente);
        tierList.setTierElement(category);
        return tierList;
    }
}
