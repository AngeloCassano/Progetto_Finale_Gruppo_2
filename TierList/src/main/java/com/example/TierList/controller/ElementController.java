package com.example.TierList.controller;

import com.example.TierList.model.Element;
import com.example.TierList.service.ElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller REST per la gestione degli elementi di una tierlist
@RestController
@RequestMapping("/api/elements")
@RequiredArgsConstructor
public class ElementController {

    private final ElementService elementService;

    // Endpoint Get che ottiene tutti gli elementi
    @GetMapping
    public ResponseEntity<List<Element>> getAllElements() {
        return ResponseEntity.ok(elementService.findAll());
    }

    // Endpoint Get che ottiene elementi tramite il suo id
    @GetMapping("/{id}")
    public ResponseEntity<Element> getElementById(@PathVariable Long id) {
        return elementService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint Get per cercare elementi tramite il nome
    @GetMapping("/search")
    public ResponseEntity<List<Element>> searchElementsByName(@RequestParam String name) {
        return ResponseEntity.ok(elementService.findByNameContaining(name));
    }

    // ➕ POST: salva nuovo elemento
    @PostMapping
    public ResponseEntity<Element> saveElement(@RequestBody Element element) {
        Element saved = elementService.save(element);
        return ResponseEntity.ok(saved);
    }

    // Endpoint Delete per cancellare elemento tramite id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable Long id) {
        elementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Nel controller Element
    @GetMapping("/by-category/{categoryId}")
    public List<Element> getElementsByCategory(@PathVariable Long categoryId) {
        return elementService.findByCategoryId(categoryId);
    }
}
