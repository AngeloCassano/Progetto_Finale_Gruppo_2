package com.example.TierList.mapper;

import com.example.TierList.dto.TierListElementDTO;
import com.example.TierList.model.Image;
import com.example.TierList.model.TierList;
import com.example.TierList.model.TierElement;

public class TierListElementMapper {


    public static TierListElementDTO toDTO(TierElement entity) {
        if (entity == null) return null;

        TierListElementDTO dto = new TierListElementDTO();
        dto.setId(entity.getId());

        dto.setTierListId(entity.getTierlist().getId());
        dto.setTierListTitle(entity.getTierlist().getTitolo());

        dto.setElementId(entity.getCategory().getId());
        dto.setElementName(entity.getCategory().getName());
        dto.setImageUrl(entity.getCategory().getImageUrl());

        dto.setTierName(entity.getName());
        return dto;
    }


    public static TierElement toEntity(TierListElementDTO dto, TierList tierList, Image element) {
        if (dto == null) return null;

        TierElement tle = new TierElement();
        tle.setId(dto.getId());
        tle.setTierlist(tierList);
        tle.setTierElement(element);
        tle.setName(dto.getTierName());
        return tle;
    }
}