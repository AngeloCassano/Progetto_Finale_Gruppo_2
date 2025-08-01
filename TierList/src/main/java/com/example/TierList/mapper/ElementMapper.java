package com.example.TierList.mapper;

import com.example.TierList.dto.ElementDTO;
import com.example.TierList.model.Element;

public class ElementMapper {

    public static ElementDTO toDTO(Element element) {
        if (element == null)
            return null;
        ElementDTO dto = new ElementDTO();
        dto.setId(element.getId());
        dto.setName(element.getName());
        dto.setImageUrl(element.getImageUrl());
        return dto;
    }

    public static Element toEntity(ElementDTO dto) {
        if (dto == null)
            return null;
        Element element = new Element();
        element.setId(dto.getId());
        element.setName(dto.getName());
        element.setImageUrl(dto.getImageUrl());
        return element;
    }
}