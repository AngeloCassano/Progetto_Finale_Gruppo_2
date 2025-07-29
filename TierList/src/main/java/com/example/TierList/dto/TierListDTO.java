package com.example.TierList.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TierListDTO {

    private Long id;
    private String titolo;
    private Long utenteId;
    private String username;
    private Long categoryId;
    private String categoryName;
    
}
