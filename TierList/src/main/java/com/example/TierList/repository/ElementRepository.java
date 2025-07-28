package com.example.TierList.repository;

import com.example.TierList.model.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
    // Trova elementi per nome (case-insensitive)
    List<Element> findByNameContainingIgnoreCase(String name);

    // Trova elementi appartenenti a una specifica categoria
    List<Element> findByCategory_Id(Long categoryId);

    // Trova un elemento per nome all'interno di una specifica categoria
    Optional<Element> findByNameAndCategory_Id(String name, Long categoryId);
}
