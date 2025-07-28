package com.example.TierList.repository;

import com.example.TierList.model.Element;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends CrudRepository<Element, Integer> {
    // Tutti i metodi CRUD già pronti!
}
