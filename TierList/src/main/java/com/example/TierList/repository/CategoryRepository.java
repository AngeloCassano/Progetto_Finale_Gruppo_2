package com.example.TierList.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TierList.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
Optional<Category> findByCategoryName(String categoryName);
}
