package com.example.TierList.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tierlistelement")
public class TierListElement {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    @OneToMany(mappedBy = "tierList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Element> element;

    
    @ManyToOne
    @JoinColumn(name = "tierlist_id", nullable = false)
    private TierList tierlist;

    @Enumerated(EnumType.STRING)
    private TierNameType tierName;


}
