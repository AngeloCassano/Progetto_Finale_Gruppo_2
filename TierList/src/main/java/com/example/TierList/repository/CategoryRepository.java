package com.example.TierList.repository;


import com.example.TierList.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    // Tutti i metodi CRUD già pronti!
}
