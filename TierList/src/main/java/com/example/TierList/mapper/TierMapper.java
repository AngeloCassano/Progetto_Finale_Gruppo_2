package com.example.TierList.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.TierList.dto.ElementDTO;
import com.example.TierList.dto.TierDTO;
import com.example.TierList.model.Element;
import com.example.TierList.model.Tier;
import com.example.TierList.model.TierList;

// È una buona pratica mettere i mapper in un pacchetto separato



public class TierMapper {

 public static TierDTO toDTO(Tier entity) {
        if (entity == null) {
            return null;
        }

        TierDTO dto = new TierDTO();
        dto.setId(entity.getId());

        // Mappa i dettagli della TierList associata al Tier
        if (entity.getTierlist() != null) {
            dto.setTierListId(entity.getTierlist().getId());
            dto.setTierListTitle(entity.getTierlist().getTitolo());
        }

        // Mappa il TierName
        dto.setTierName(entity.getTierName());

        // Mappa la lista di Elementi usando gli stream
        if (entity.getElements() != null && !entity.getElements().isEmpty()) {
            List<ElementDTO> elementDTOs = entity.getElements().stream()
                .map(elementEntity -> {
                    ElementDTO elementDto = new ElementDTO();
                    elementDto.setId(elementEntity.getId());
                    elementDto.setName(elementEntity.getName());
                    elementDto.setImageUrl(elementEntity.getImageUrl());
                    return elementDto;
                })
                .collect(Collectors.toList());
            dto.setElements(elementDTOs);
        } else {
            dto.setElements(Collections.emptyList());
        }

        return dto;
    }



    //idem da riguardare con gli stream
    public static Tier toEntity(TierDTO dto, TierList tierList, List<Element> elements) {
        if (dto == null) return null;

        Tier tle = new Tier();
        tle.setId(dto.getId());
        tle.setTierlist(tierList);
        tle.setElements(elements);
        tle.setTierName(dto.getTierName());
        return tle;
    }
}