package com.example.TierList.mapper;

import com.example.TierList.dto.TierListDTO;
import com.example.TierList.model.Category;
import com.example.TierList.model.TierList;
import com.example.TierList.model.Utente;

public class TierListMapper
{
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

    public static TierList toEntity(TierListDTO dto)
    {
        if (dto == null) return null;

        TierList tierList = new TierList();

        Utente utente = null;

        if (dto.getUtenteId() != null) {
            utente = new Utente();
            utente.setId(dto.getUtenteId());
        }

        Category category = null;
        
        if (dto.getCategoryId() != null) {
            category = new Category();
            category.setId(dto.getCategoryId());
        }

        tierList.setId(dto.getId());
        tierList.setTitolo(dto.getTitolo());
        tierList.setUtente(utente);
        tierList.setCategory(category);
        return tierList;
    }
}
