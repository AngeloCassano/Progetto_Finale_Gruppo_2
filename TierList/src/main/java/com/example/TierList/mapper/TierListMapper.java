package com.example.TierList.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.TierList.dto.TierDTO;
import com.example.TierList.dto.TierListDTO;
import com.example.TierList.model.Category;
import com.example.TierList.model.TierList;
import com.example.TierList.model.Utente;

public class TierListMapper {

 /**
     * Converte un'entità TierList in un TierListDTO,
     * includendo i dettagli di Utente, Category e una lista di TierDTO (con i loro ElementDTO).
     *
     * @param tierList L'entità TierList da convertire.
     * @return Un TierListDTO che rappresenta l'entità, o null se l'entità è null.
     */
    public static TierListDTO toDTO(TierList tierList) {
        if (tierList == null) {
            return null;
        }

        TierListDTO dto = new TierListDTO();
        dto.setId(tierList.getId());
        dto.setTitolo(tierList.getTitolo());

        // Mappa i dettagli dell'Utente (con controllo null)
        if (tierList.getUtente() != null) {
            dto.setUtenteId(tierList.getUtente().getId());
            dto.setUsername(tierList.getUtente().getUsername());
        }

        // Mappa i dettagli della Categoria (con controllo null)
        if (tierList.getCategory() != null) {
            dto.setCategoryId(tierList.getCategory().getId());
            dto.setCategoryName(tierList.getCategory().getCategoryName());
        }

        // --- MAPPA LA LISTA DI TIER USANDO GLI STREAM ---
        // Se la lista di Tier nell'entità è presente e non vuota
        if (tierList.getTiers() != null && !tierList.getTiers().isEmpty()) {
            List<TierDTO> tierDTOs = tierList.getTiers().stream()
                // Per ogni 'Tier' nell'entità, usa il 'TierMapper' per convertirlo in 'TierDTO'
                .map(TierMapper::toDTO)
                .collect(Collectors.toList()); // Raccogli i risultati in una List<TierDTO>
            dto.setTiers(tierDTOs);
        } else {
            // Se non ci sono Tier, imposta una lista vuota per prevenire NullPointerExceptions nel DTO
            dto.setTiers(Collections.emptyList());
        }

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
