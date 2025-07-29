package com.example.TierList.service;

import com.example.TierList.model.Element;
import com.example.TierList.repository.ElementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElementService {

    private final ElementRepository elementRepository;  // Elemento per il richiamo dei metodi del crud piu i metodi dell elemento repository

    // Metodo per ottenere tutti gli elementi
    public List<Element> getAllElements() {
        return elementRepository.findAll();
    }

    // Metodo per ottenere un elemento tramite id
    public Optional<Element> getElementById(Long id) {
        return elementRepository.findById(id);
    }

    // Metodo per salvare un elemento 
    public Element saveElement(Element element) {
        return elementRepository.save(element);
    }

    // Metodo per cancellare un elemento
    public void deleteElement(Long id) {
        elementRepository.deleteById(id);
    }

    // Metodo per cercare tutti gli elementi tramite il nome
    public List<Element> searchElementsByName(String name) {
        return elementRepository.findByNameContainingIgnoreCase(name);
    }

    // Metodo per cercare tutti gli elementi tramite categoria 
    public List<Element> getElementsByCategoryId(Long categoryId) {
        return elementRepository.findByCategory_Id(categoryId);
    }

    // Metodo per cercare un elemento tramite nome e categoria
    public Optional<Element> getElementByNameAndCategoryId(String name, Long categoryId) {
        return elementRepository.findByNameAndCategory_Id(name, categoryId);
    }
}