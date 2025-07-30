package com.example.TierList.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List; // Importa List

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TierListDTO {

    private Long id;
    private String titolo;

    // Puoi scegliere se includere solo gli ID e i nomi, o i DTO completi.
    // L'esempio del mapper userà solo ID e nomi come nel tuo attuale DTO.
    private Long utenteId;
    private String username;

    private Long categoryId;
    private String categoryName;

    // *** Aggiungi qui la lista dei TierDTO! ***
    private List<TierDTO> tiers;
}
