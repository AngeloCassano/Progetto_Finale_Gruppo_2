package com.example.TierList.repository;

import com.example.TierList.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Metodo per trovare una categoria per nome, utile per evitare duplicati
    Optional<Category> findByCategoryName(String categoryName);
}
