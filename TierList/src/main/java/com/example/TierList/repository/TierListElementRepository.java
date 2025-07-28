package com.example.TierList.repository;


import com.example.TierList.model.TierListElement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierListElementRepository extends JpaRepository<TierListElement, Long> {

    List<TierListElementRepository> findByTierListId(Long tierListId);
    // Tutti i metodi CRUD già pronti!

    List<TierListElementRepository> findByElementId(Long elementId);
}
