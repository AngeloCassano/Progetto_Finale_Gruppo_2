package com.example.TierList.repository;


import com.example.TierList.model.TierListElement;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierListElementRepository extends CrudRepository<TierListElement, Long> {

    List<TierListElementRepository> findByTierListId(Long tierListId);
    // Tutti i metodi CRUD già pronti!

    List<TierListElementRepository> findByElementId(Long elementId);
}
