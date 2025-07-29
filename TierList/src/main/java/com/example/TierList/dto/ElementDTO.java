package com.example.TierList.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private Long categoryId;
    private String categoryName;   
}
