package com.example.TierList.service;

import com.example.TierList.model.Element;
import com.example.TierList.repository.ElementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service 
public class ElementService {

    private final ElementRepository elementRepository;

  
    public ElementService(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

  
    public List<Element> getAllElements() {
        return elementRepository.findAll();
    }

 
    public Optional<Element> getElementById(Long id) {
        return elementRepository.findById(id);
    }

   
    public Element saveElement(Element element) {
        return elementRepository.save(element);
    }

   
    public void deleteElement(Long id) {
        elementRepository.deleteById(id);
    }

   
    public List<Element> searchElementsByName(String name) {
        return elementRepository.findByName(name);
    }


    public List<Element> getElementsByCategoryId(Long categoryId) {
        return elementRepository.findByCategoryId(categoryId);
    }
}
