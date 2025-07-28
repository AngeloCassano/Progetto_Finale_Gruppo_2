package com.example.TierList.repository;


import com.example.TierList.model.TierList;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierListRepository extends CrudRepository<TierList, Long> {

    List<TierListRepository> findByUtenteId(Long utenteId);
    // Tutti i metodi CRUD già pronti!

    List<TierListRepository> findByCatgoryId(Long categoryId);
}
