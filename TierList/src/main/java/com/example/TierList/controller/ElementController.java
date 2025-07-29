package com.example.TierList.controller;

import com.example.TierList.model.Element;
import com.example.TierList.service.ElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elements")
@RequiredArgsConstructor
public class ElementController {

    private final ElementService elementService;

    // 🔍 GET: tutti gli elementi
    @GetMapping
    public ResponseEntity<List<Element>> getAllElements() {
        return ResponseEntity.ok(elementService.getAllElements());
    }

    // 🔍 GET: elemento per ID
    @GetMapping("/{id}")
    public ResponseEntity<Element> getElementById(@PathVariable Long id) {
        return elementService.getElementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔎 GET: cerca per nome
    @GetMapping("/search")
    public ResponseEntity<List<Element>> searchElementsByName(@RequestParam String name) {
        return ResponseEntity.ok(elementService.searchElementsByName(name));
    }

    // ➕ POST: salva nuovo elemento
    @PostMapping
    public ResponseEntity<Element> saveElement(@RequestBody Element element) {
        Element saved = elementService.saveElement(element);
        return ResponseEntity.ok(saved);
    }

    // ❌ DELETE: elimina elemento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable Long id) {
        elementService.deleteElement(id);
        return ResponseEntity.noContent().build();
    }
}
