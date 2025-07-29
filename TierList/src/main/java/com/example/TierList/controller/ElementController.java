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

    //Endpoint Get che ottiene tutti gli elementi
    @GetMapping
    public ResponseEntity<List<Element>> getAllElements() {
        return ResponseEntity.ok(elementService.getAllElements());
    }

   //Endpoint Get che ottiene elementi tramite il suo id
    @GetMapping("/{id}")
    public ResponseEntity<Element> getElementById(@PathVariable Long id) {
        return elementService.getElementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Endpoint Get per cercare elementi tramite il nome
    @GetMapping("/search")
    public ResponseEntity<List<Element>> searchElementsByName(@RequestParam String name) {
        return ResponseEntity.ok(elementService.searchElementsByName(name));
    }

<<<<<<< HEAD
    //Endpoint Get per ottenere elementi tramite categoria
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Element>> getElementsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(elementService.getElementsByCategoryId(categoryId));
    }

    //Endpoint Get che ottiene elementi tramite nome e categoria
    @GetMapping("/check")
    public ResponseEntity<Element> getElementByNameAndCategoryId(
            @RequestParam String name,
            @RequestParam Long categoryId
    ) {
        return elementService.getElementByNameAndCategoryId(name, categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Endpoint Post che salva nuovo elemento
=======
    // ➕ POST: salva nuovo elemento
>>>>>>> df102c4e8438571f12f8b3252f4ddff776700903
    @PostMapping
    public ResponseEntity<Element> saveElement(@RequestBody Element element) {
        Element saved = elementService.saveElement(element);
        return ResponseEntity.ok(saved);
    }


    //Endpoint Delete per cancellare elemento tramite id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable Long id) {
        elementService.deleteElement(id);
        return ResponseEntity.noContent().build();
    }
}
