// ElementService.java
package com.example.TierList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TierList.model.Element;
import com.example.TierList.model.Category;
import com.example.TierList.repository.ElementRepository;
import com.example.TierList.repository.CategoryRepository;

@Service
public class ElementService {

    @Autowired
    private ElementRepository elementRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    // Metodi esistenti
    public List<Element> findAll() {
        return elementRepository.findAll();
    }

    public Optional<Element> findById(Long id) {
        return elementRepository.findById(id);
    }

    public Element save(Element element) {
        return elementRepository.save(element);
    }

    public void deleteById(Long id) {
        elementRepository.deleteById(id);
    }

    public List<Element> findByNameContaining(String name) {
        return elementRepository.findByNameContainingIgnoreCase(name);
    }

    // public Optional<Element> findByName(String name) {
    //     return elementRepository.findByName(name);
    // }

    // NUOVI METODI per Category
    public List<Element> findByCategoryId(Long categoryId) {
        return elementRepository.findByCategoryId(categoryId);
    }
    
    public List<Element> findByCategoryIdAndTierId(Long categoryId, Long tierId) {
        return elementRepository.findByCategoryIdAndTierId(categoryId, tierId);
    }
    
    public List<Element> findUnassignedElementsByCategory(Long categoryId) {
        return elementRepository.findByCategoryIdAndTierIsNull(categoryId);
    }
    
    public long countElementsByCategory(Long categoryId) {
        return elementRepository.countByCategoryId(categoryId);
    }
    
    // Metodo per creare un elemento con categoria
    public Element createElement(String name, String imageUrl, Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new RuntimeException("Category not found with id: " + categoryId);
        }
        
        Element element = new Element();
        element.setName(name);
        element.setImageUrl(imageUrl);
        element.setCategory(category.get());
        // tier rimane null inizialmente
        
        return elementRepository.save(element);
    }
    
    // Metodo per assegnare un elemento a un tier
    public Element assignElementToTier(Long elementId, Long tierId) {
        Optional<Element> elementOpt = elementRepository.findById(elementId);
        if (elementOpt.isEmpty()) {
            throw new RuntimeException("Element not found with id: " + elementId);
        }
        
        Element element = elementOpt.get();
        // Qui dovresti anche verificare che il tier esista
        // Tier tier = tierRepository.findById(tierId).orElseThrow(...)
        // element.setTier(tier);
        
        return elementRepository.save(element);
    }
    
    // Metodo per rimuovere un elemento da un tier
    public Element removeElementFromTier(Long elementId) {
        Optional<Element> elementOpt = elementRepository.findById(elementId);
        if (elementOpt.isEmpty()) {
            throw new RuntimeException("Element not found with id: " + elementId);
        }
        
        Element element = elementOpt.get();
        element.setTier(null);
        
        return elementRepository.save(element);
    }
}