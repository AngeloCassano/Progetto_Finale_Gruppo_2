package com.example.TierList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.TierList.model.Element;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
    // Trova elementi per nome (case-insensitive)
    List<Element> findByNameContainingIgnoreCase(String name);

    // NUOVO: Trova elementi per categoria
    List<Element> findByCategoryId(Long categoryId);
    
    // NUOVO: Trova elementi per categoria e tier
    @Query("SELECT e FROM Element e WHERE e.category.id = :categoryId AND e.tier.id = :tierId")
    List<Element> findByCategoryIdAndTierId(@Param("categoryId") Long categoryId, @Param("tierId") Long tierId);
    
    // NUOVO: Trova elementi non assegnati a nessun tier per una categoria
    @Query("SELECT e FROM Element e WHERE e.category.id = :categoryId AND e.tier IS NULL")
    List<Element> findByCategoryIdAndTierIsNull(@Param("categoryId") Long categoryId);
    
    // NUOVO: Conta elementi per categoria
    long countByCategoryId(Long categoryId);
}
