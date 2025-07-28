package com.example.TierList.repository;

import com.example.TierList.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    // Trova elementi per nome (case-insensitive)
    List<Image> findByNameContainingIgnoreCase(String name);

    // Trova elementi appartenenti a una specifica categoria
    List<Image> findByCategoryId(Long categoryId);
    // List<Image> findByCategory_Id(Long categoryId);

    // Trova un elemento per nome all'interno di una specifica categoria
    Optional<Image> findByNameAndCategoryId(String name, Long categoryId);
    // Optional<Image> findByNameAndCategory_Id(String name, Long categoryId);
}
