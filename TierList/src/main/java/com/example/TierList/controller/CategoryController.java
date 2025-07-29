package com.example.TierList.controller;


import com.example.TierList.model.Category;
import com.example.TierList.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Recupera tutte le Category.
     * GET /api/categories
     * @return Una lista di tutte le Category.
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * Recupera una Category tramite il suo ID.
     * GET /api/categories/{id}
     * @param id L'ID della Category.
     * @return La Category se trovata, altrimenti HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Recupera una Category tramite il suo nome.
     * GET /api/categories/by-name
     * @param categoryName Il nome della Category.
     * @return La Category se trovata, altrimenti HttpStatus.NOT_FOUND.
     */
    @GetMapping("/by-name")
    public ResponseEntity<Category> getCategoryByCategoryName(@RequestParam String categoryName) {
        Optional<Category> category = categoryService.getCategoryByCategoryName(categoryName);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Salva una nuova Category o aggiorna una esistente.
     * POST /api/categories
     * @param category L'oggetto Category da salvare/aggiornare.
     * @return La Category salvata/aggiornata.
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    /**
     * Aggiorna una Category esistente.
     * PUT /api/categories/{id}
     * @param id L'ID della Category da aggiornare.
     * @param category Il corpo della Category aggiornata.
     * @return La Category aggiornata se trovata, altrimenti HttpStatus.NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        // Verifica se la Category esiste prima di aggiornarla
        if (categoryService.getCategoryById(id).isPresent()) {
            category.setId(id); // Assicura che l'ID nel corpo corrisponda all'ID del percorso
            Category updatedCategory = categoryService.saveCategory(category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una Category tramite il suo ID.
     * DELETE /api/categories/{id}
     * @param id L'ID della Category da eliminare.
     * @return HttpStatus.NO_CONTENT se eliminata con successo, altrimenti HttpStatus.NOT_FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.getCategoryById(id).isPresent()) {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}