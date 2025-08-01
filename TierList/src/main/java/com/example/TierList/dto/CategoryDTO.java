package com.example.TierList.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
     private Long id;
    private String categoryName;
    private String descrizione;
     private List<TierListDTO> tierlists;
}
