package com.example.TierList.controller;


import com.example.TierList.model.TierList;
import com.example.TierList.service.TierListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Controller REST per la gestione delle tierlist
@RestController
@RequestMapping("/api/tierlists")
@RequiredArgsConstructor
public class TierListController {

    private final TierListService tierListService;

   
    //Endpoint Get per recuperare tutte le tierlist
    @GetMapping
    public ResponseEntity<List<TierList>> getAllTierLists() {
        List<TierList> tierLists = tierListService.getAllTierLists();
        return new ResponseEntity<>(tierLists, HttpStatus.OK);
    }

    
    //Endpoint Get che recupera tierlist tramite id
    @GetMapping("/{id}")
    public ResponseEntity<TierList> getTierListById(@PathVariable Long id) {
        Optional<TierList> tierList = tierListService.getTierListById(id);
        return tierList.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    //Endpoint Post che salva nuova tierlist
    @PostMapping
    public ResponseEntity<TierList> createTierList(@RequestBody TierList tierList) {
        TierList savedTierList = tierListService.saveTierList(tierList);
        return new ResponseEntity<>(savedTierList, HttpStatus.CREATED);
    }

   
    //Endpoint Put che aggiorna tierlist esistente
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

   
    //Endpoint Delete che cancella tierlist
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTierList(@PathVariable Long id) {
        if (tierListService.getTierListById(id).isPresent()) {
            tierListService.deleteTierList(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    //Endpoint Get che Recupera tutte le TierList create da un utente specifico
    @GetMapping("/by-user/{utenteId}")
    public ResponseEntity<List<TierList>> getTierListsByUtenteId(@PathVariable Long utenteId) {
        List<TierList> tierLists = tierListService.getTierListsByUtenteId(utenteId);
        return new ResponseEntity<>(tierLists, HttpStatus.OK);
    }

<<<<<<< HEAD
 
    //Endpoint Get che recupera tutte le TierList associate a una specifica categoria
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<TierList>> getTierListsByCategoryId(@PathVariable Long categoryId) {
        List<TierList> tierLists = tierListService.getTierListsByCategoryId(categoryId);
        return new ResponseEntity<>(tierLists, HttpStatus.OK);
    }


    //Endpoint Get che cerca TierList per titolo (case-insensitive)
=======
    /**
     * Cerca TierList per titolo (case-insensitive).
     * GET /api/tierlists/search-by-title
     * @param titolo Il titolo da cercare.
     * @return Un Optional contenente la TierList se trovata, altrimenti HttpStatus.NOT_FOUND.
     */
>>>>>>> df102c4e8438571f12f8b3252f4ddff776700903
    @GetMapping("/search-by-title")
    public ResponseEntity<TierList> searchTierListByTitolo(@RequestParam String titolo) {
        Optional<TierList> tierList = tierListService.searchTierListByTitolo(titolo);
        return tierList.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

<<<<<<< HEAD
   
    //Endpoint Get che eecupera le TierList di un utente specifico appartenenti a una certa categoria
    @GetMapping("/by-user-and-category")
    public ResponseEntity<List<TierList>> getTierListsByUtenteAndCategory(
            @RequestParam Long utenteId,
            @RequestParam Long categoryId) {
        List<TierList> tierLists = tierListService.getTierListsByUtenteAndCategory(utenteId, categoryId);
        return new ResponseEntity<>(tierLists, HttpStatus.OK);
    }
=======

>>>>>>> df102c4e8438571f12f8b3252f4ddff776700903
}