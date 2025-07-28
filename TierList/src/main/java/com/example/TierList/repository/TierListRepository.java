package com.example.TierList.repository;


import com.example.TierList.model.TierList;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierListRepository extends JpaRepository<TierList, Long> {

    List<TierListRepository> findByUtenteId(Long utenteId);
    List<TierListRepository> findByCatgoryId(Long categoryId);
}
