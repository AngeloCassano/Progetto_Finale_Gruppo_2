package com.example.TierList.dto;

import com.example.TierList.model.TierNameType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TierListElementDTO {
    private Long id;

    private Long tierListId;
    private String tierListTitle;

    private Long elementId;
    private String elementName;
    private String imageUrl;

   private TierNameType tierName;
}

