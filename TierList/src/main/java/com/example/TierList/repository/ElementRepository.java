package com.example.TierList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TierList.model.Element;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
    List<Element> findByName(String name);
    List<Element> findByCategoryId(Long categoryId);
}
