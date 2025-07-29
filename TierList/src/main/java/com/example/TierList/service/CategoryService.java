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

    private final CategoryRepository categoryRepository;       // Elemento della category repository per utilizzare tutti i metodi jpa e i metodi aggiunti a mano nella classe

 
    public List<Category> getAllCategories() {          // Visualizza tutte le categorie
        return categoryRepository.findAll();
    }

  
    public Optional<Category> getCategoryById(Long id) {    // Visualizza le categorie tramite id
        return categoryRepository.findById(id);
    }

    
    public Optional<Category> getCategoryByCategoryName(String categoryName) {      // Visualizza tutte le categorie tramite name
        return categoryRepository.findByCategoryName(categoryName);
    }

   
    public Category saveCategory(Category category) {               // Salva le categorie
        return categoryRepository.save(category);
    }

   
    public void deleteCategory(Long id) {               // Cancella le categorie tramite id
        categoryRepository.deleteById(id);
    }
}
