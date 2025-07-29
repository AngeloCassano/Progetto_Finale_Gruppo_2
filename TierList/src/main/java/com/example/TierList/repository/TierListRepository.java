package com.example.TierList.repository;

import com.example.TierList.model.TierList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TierListRepository extends JpaRepository<TierList, Long> {
    // Trova TierList per l'utente che l'ha creata
    List<TierList> findByUtente_Id(Long utenteId);

    // Trova TierList per la categoria a cui appartiene
    List<TierList> findByCategory_Id(Long categoryId);

    // Trova TierList per titolo (case-insensitive)
    Optional<TierList> findByTitoloContainingIgnoreCase(String titolo);
}
