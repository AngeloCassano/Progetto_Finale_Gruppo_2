package com.example.TierList.mapper;

import com.example.TierList.dto.TierListElementDTO;
import com.example.TierList.model.Element;
import com.example.TierList.model.TierList;
import com.example.TierList.model.TierListElement;

public class TierListElementMapper {


    public static TierListElementDTO toDTO(TierListElement entity) {
        if (entity == null) return null;

        TierListElementDTO dto = new TierListElementDTO();
        dto.setId(entity.getId());

        dto.setTierListId(entity.getTierlist().getId());
        dto.setTierListTitle(entity.getTierlist().getTitolo());

        dto.setElementId(entity.getElement().getId());
        dto.setElementName(entity.getElement().getName());
        dto.setImageUrl(entity.getElement().getImageUrl());

        dto.setTierName(entity.getTierName());
        return dto;
    }


    public static TierListElement toEntity(TierListElementDTO dto, TierList tierList, Element element) {
        if (dto == null) return null;

        TierListElement tle = new TierListElement();
        tle.setId(dto.getId());
        tle.setTierlist(tierList);
        tle.setElement(element);
        tle.setTierName(dto.getTierName());
        return tle;
    }
}