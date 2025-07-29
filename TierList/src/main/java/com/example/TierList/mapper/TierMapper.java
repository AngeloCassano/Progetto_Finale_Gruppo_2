package com.example.TierList.mapper;

import java.util.List;

import com.example.TierList.dto.TierDTO;
import com.example.TierList.model.Element;
import com.example.TierList.model.Tier;
import com.example.TierList.model.TierList;


public class TierMapper {

/*++++++++++++++++++++IMPORTANTE+++++++++++++++ */
    //bisogna sistemare con gli stream per ottenere l'elemento
    public static TierDTO toDTO(Tier entity) {
        if (entity == null) return null;

        TierDTO dto = new TierDTO();
        dto.setId(entity.getId());

        dto.setTierListId(entity.getTierlist().getId());
        dto.setTierListTitle(entity.getTierlist().getTitolo());

        dto.setElementId(entity.getElements().get(1).getId());
        dto.setElementName(entity.getElements().get(1).getName());
        dto.setImageUrl(entity.getElements().get(1).getImageUrl());

        dto.setTierName(entity.getTierName());
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