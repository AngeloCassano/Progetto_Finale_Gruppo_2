package com.example.TierList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TierList.model.Tier;

@Repository
public interface TierRepository extends JpaRepository<Tier, Long> {
    // Trova tutti gli elementi all'interno di una specifica TierList
    List<Tier> findByTierlist_Id(Long tierlistId); // Nota: 'tierlist' (campo in Tier)

    // Trova tutte le istanze di un Element specifico usate nelle TierList
    List<Tier> findByElements_Id(Long elementsId); // Nota: 'elements' (campo in Tier)
}