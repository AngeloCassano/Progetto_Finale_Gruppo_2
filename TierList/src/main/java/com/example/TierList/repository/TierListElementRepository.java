package com.example.TierList.repository;

import com.example.TierList.model.TierElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TierListElementRepository extends JpaRepository<TierElement, Long> {
    // Trova tutti gli elementi all'interno di una specifica TierList
    List<TierElement> findByTierlist_Id(Long tierlistId); // Nota: 'tierlist' (campo in TierListElement)

    // Trova tutte le istanze di un Element specifico usate nelle TierList
    List<TierElement> findByElement_Id(Long elementId); // Nota: 'element' (campo in TierListElement)

    // Trova un TierListElement specifico per la TierList e l'Element
    Optional<TierElement> findByTierlist_IdAndElement_Id(Long tierlistId, Long elementId);
}