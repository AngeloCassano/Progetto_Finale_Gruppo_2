package com.example.TierList.controller;


import com.example.TierList.model.TierList;
import com.example.TierList.service.TierListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tierlists")
@RequiredArgsConstructor
public class TierListController {

    private final TierListService tierListService;

    /**
     * Recupera tutte le TierList.
     * GET /api/tierlists
     * @return Una lista di tutte le TierList.
     */
    @GetMapping
    public ResponseEntity<List<TierList>> getAllTierLists() {
        List<TierList> tierLists = tierListService.getAllTierLists();
        return new ResponseEntity<>(tierLists, HttpStatus.OK);
    }

    /**
     * Recupera una TierList tramite il suo ID.
     * GET /api/tierlists/{id}
     * @param id L'ID della TierList.
     * @return La TierList se trovata, altrimenti HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TierList> getTierListById(@PathVariable Long id) {
        Optional<TierList> tierList = tierListService.getTierListById(id);
        return tierList.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Salva una nuova TierList o aggiorna una esistente.
     * POST /api/tierlists
     * @param tierList L'oggetto TierList da salvare/aggiornare.
     * @return La TierList salvata/aggiornata.
     */
    @PostMapping
    public ResponseEntity<TierList> createTierList(@RequestBody TierList tierList) {
        TierList savedTierList = tierListService.saveTierList(tierList);
        return new ResponseEntity<>(savedTierList, HttpStatus.CREATED);
    }

    /**
     * Aggiorna una TierList esistente.
     * PUT /api/tierlists/{id}
     * @param id L'ID della TierList da aggiornare.
     * @param tierList Il corpo della TierList aggiornata.
     * @return La TierList aggiornata se trovata, altrimenti HttpStatus.NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TierList> updateTierList(@PathVariable Long id, @RequestBody TierList tierList) {
        // Verifica se la TierList esiste prima di aggiornarla
        if (tierListService.getTierListById(id).isPresent()) {
            tierList.setId(id); // Assicura che l'ID nel corpo corrisponda all'ID del percorso
            TierList updatedTierList = tierListService.saveTierList(tierList);
            return new ResponseEntity<>(updatedTierList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una TierList tramite il suo ID.
     * DELETE /api/tierlists/{id}
     * @param id L'ID della TierList da eliminare.
     * @return HttpStatus.NO_CONTENT se eliminata con successo, altrimenti HttpStatus.NOT_FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTierList(@PathVariable Long id) {
        if (tierListService.getTierListById(id).isPresent()) {
            tierListService.deleteTierList(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Recupera tutte le TierList create da un utente specifico.
     * GET /api/tierlists/by-user/{utenteId}
     * @param utenteId L'ID dell'utente.
     * @return Una lista di TierList create dall'utente specificato.
     */
    @GetMapping("/by-user/{utenteId}")
    public ResponseEntity<List<TierList>> getTierListsByUtenteId(@PathVariable Long utenteId) {
        List<TierList> tierLists = tierListService.getTierListsByUtenteId(utenteId);
        return new ResponseEntity<>(tierLists, HttpStatus.OK);
    }

    /**
     * Recupera tutte le TierList associate a una specifica categoria.
     * GET /api/tierlists/by-category/{categoryId}
     * @param categoryId L'ID della categoria.
     * @return Una lista di TierList associate alla categoria specificata.
     */
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<TierList>> getTierListsByCategoryId(@PathVariable Long categoryId) {
        List<TierList> tierLists = tierListService.getTierListsByCategoryId(categoryId);
        return new ResponseEntity<>(tierLists, HttpStatus.OK);
    }

    /**
     * Cerca TierList per titolo (case-insensitive).
     * GET /api/tierlists/search-by-title
     * @param titolo Il titolo da cercare.
     * @return Un Optional contenente la TierList se trovata, altrimenti HttpStatus.NOT_FOUND.
     */
    @GetMapping("/search-by-title")
    public ResponseEntity<TierList> searchTierListByTitolo(@RequestParam String titolo) {
        Optional<TierList> tierList = tierListService.searchTierListByTitolo(titolo);
        return tierList.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Recupera le TierList di un utente specifico appartenenti a una certa categoria.
     * GET /api/tierlists/by-user-and-category
     * @param utenteId L'ID dell'utente.
     * @param categoryId L'ID della categoria.
     * @return Una lista di TierList che corrispondono ai criteri.
     */
    @GetMapping("/by-user-and-category")
    public ResponseEntity<List<TierList>> getTierListsByUtenteAndCategory(
            @RequestParam Long utenteId,
            @RequestParam Long categoryId) {
        List<TierList> tierLists = tierListService.getTierListsByUtenteAndCategory(utenteId, categoryId);
        return new ResponseEntity<>(tierLists, HttpStatus.OK);
    }
}