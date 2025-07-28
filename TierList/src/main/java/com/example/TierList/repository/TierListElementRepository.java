package com.example.TierList.repository;


import com.example.TierList.model.TierListElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierListElementRepository extends CrudRepository<TierListElement, Integer> {
    // Tutti i metodi CRUD già pronti!
}
