package com.example.TierList.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.TierList.dto.CategoryDTO;
import com.example.TierList.dto.TierListDTO;
import com.example.TierList.model.Category;

public class CategoryMapper {

/**
     * Converte un'entità Category in un CategoryDTO,
     * includendo i dettagli diretti e una lista di TierListDTO associate.
     *
     * @param category L'entità Category da convertire.
     * @return Un CategoryDTO che rappresenta l'entità, o null se l'entità è null.
     */
    public static CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());
        dto.setDescrizione(category.getDescrizione());

        // --- MAPPA LA LISTA DI TIERLIST USANDO GLI STREAM ---
        // Se la lista di TierList nell'entità è presente e non vuota
        if (category.getTierlists() != null && !category.getTierlists().isEmpty()) {
            List<TierListDTO> tierListDTOs = category.getTierlists().stream()
                // Per ogni 'TierList' nell'entità, usa il 'TierListMapper' per convertirlo in 'TierListDTO'
                .map(TierListMapper::toDTO) // Utilizza il TierListMapper che abbiamo definito
                .collect(Collectors.toList()); // Raccogli i risultati in una List<TierListDTO>
            dto.setTierlists(tierListDTOs);
        } else {
            // Se non ci sono TierList, imposta una lista vuota per prevenire NullPointerExceptions nel DTO
            dto.setTierlists(Collections.emptyList());
        }

        return dto;
    }

   
    public static Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setCategoryName(dto.getCategoryName());
        entity.setDescrizione(dto.getDescrizione());
    
        return entity;
    }
    
}