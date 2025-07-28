package com.example.TierList.repository;


import com.example.TierList.model.TierList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierListRepository extends CrudRepository<TierList, Long> {
    // Tutti i metodi CRUD già pronti!
}
