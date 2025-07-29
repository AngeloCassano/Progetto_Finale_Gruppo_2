package com.example.TierList.service;

import com.example.TierList.model.Category;
import com.example.TierList.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor 
public class CategoryService {

    private final CategoryRepository categoryRepository;

 
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

  
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    
    public Optional<Category> getCategoryByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

   
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

   
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
