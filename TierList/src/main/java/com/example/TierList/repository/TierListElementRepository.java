package com.example.TierList.repository;

import com.example.TierList.model.TierListElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TierListElementRepository extends JpaRepository<TierListElement, Long> {
    // Trova tutti gli elementi all'interno di una specifica TierList
    List<TierListElement> findByTierlist_Id(Long tierlistId); // Nota: 'tierlist' (campo in TierListElement)

    // Trova tutte le istanze di un Element specifico usate nelle TierList
    List<TierListElement> findByElement_Id(Long elementId); // Nota: 'element' (campo in TierListElement)

    // Trova un TierListElement specifico per la TierList e l'Element
    Optional<TierListElement> findByTierlist_IdAndElement_Id(Long tierlistId, Long elementId);
}