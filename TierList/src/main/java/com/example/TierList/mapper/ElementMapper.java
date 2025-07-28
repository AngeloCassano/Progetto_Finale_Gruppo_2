package com.example.TierList.mapper;

import com.example.TierList.dto.ElementDTO;
import com.example.TierList.model.Category;
import com.example.TierList.model.Image;

public class ElementMapper {

    public static ElementDTO toDTO(Image element) {
        if (element == null) return null;
        ElementDTO dto = new ElementDTO();
        dto.setId(element.getId());
        dto.setName(element.getName());
        dto.setImageUrl(element.getImageUrl());
        dto.setCategoryId(element.getTierElement().getId());
        dto.setCategoryName(element.getTierElement().getName());
        return dto;
    }

    public static Image toEntity(ElementDTO dto, Category category) {
        if (dto == null) return null;
        Image element = new Image();
        element.setId(dto.getId());
        element.setName(dto.getName());
        element.setImageUrl(dto.getImageUrl());
        element.setTierElement(category);
        return element;
    }
}