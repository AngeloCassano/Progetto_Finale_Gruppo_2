package com.example.TierList.mapper;

import com.example.TierList.dto.CategoryDTO;
import com.example.TierList.model.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        if (category == null) return null;
        return new CategoryDTO(category.getId(), category.getName(), category.getDescrizione());
    }

    public static Category toEntity(CategoryDTO dto) {
        if (dto == null) return null;
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getCategoryName());
        category.setDescrizione(dto.getDescrizione());
        return category;
    }
}