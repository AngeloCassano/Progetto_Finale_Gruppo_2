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

    private final ElementRepository elementRepository;

    /**
     * Recupera tutti gli elementi.
     * @return Una lista di tutti gli Element.
     */
    public List<Element> getAllElements() {
        return elementRepository.findAll();
    }

    /**
     * Recupera un elemento tramite il suo ID.
     * @param id L'ID dell'elemento.
     * @return Un Optional contenente l'Element se trovato.
     */
    public Optional<Element> getElementById(Long id) {
        return elementRepository.findById(id);
    }

    /**
     * Salva un nuovo elemento o aggiorna uno esistente.
     * @param element L'oggetto Element da salvare/aggiornare.
     * @return L'Element salvato/aggiornato.
     */
    public Element saveElement(Element element) {
        return elementRepository.save(element);
    }

    /**
     * Elimina un elemento tramite il suo ID.
     * @param id L'ID dell'elemento da eliminare.
     */
    public void deleteElement(Long id) {
        elementRepository.deleteById(id);
    }

    /**
     * Cerca elementi per nome (case-insensitive).
     * @param name Il nome da cercare.
     * @return Una lista di Elementi corrispondenti.
     */
    public List<Element> searchElementsByName(String name) {
        return elementRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Recupera tutti gli elementi appartenenti a una specifica categoria.
     * @param categoryId L'ID della categoria.
     * @return Una lista di Elementi della categoria specificata.
     */
    public List<Element> getElementsByCategoryId(Long categoryId) {
        return elementRepository.findByCategory_Id(categoryId);
    }

    /**
     * Trova un elemento specifico per nome all'interno di una categoria.
     * @param name Il nome dell'elemento.
     * @param categoryId L'ID della categoria.
     * @return Un Optional contenente l'Element se trovato.
     */
    public Optional<Element> getElementByNameAndCategoryId(String name, Long categoryId) {
        return elementRepository.findByNameAndCategory_Id(name, categoryId);
    }
}