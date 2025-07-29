package com.example.TierList.controller;

import com.example.TierList.model.TierListElement;
import com.example.TierList.service.TierListElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Controller REST per la gestione delle posizioni degli elementi delle tierlist
@RestController
@RequestMapping("/api/tierlist-elements")
@RequiredArgsConstructor
public class TierListElementController {

    private final TierListElementService tierListElementService;

    
    //Endpoint Get che recupera tutti i TierListElement
    @GetMapping
    public ResponseEntity<List<TierListElement>> getAllTierListElements() {
        List<TierListElement> elements = tierListElementService.getAllTierListElements();
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

  
    //Endpoint Get che recupera un TierListElement tramite il suo ID
    @GetMapping("/{id}")
    public ResponseEntity<TierListElement> getTierListElementById(@PathVariable Long id) {
        Optional<TierListElement> element = tierListElementService.getTierListElementById(id);
        return element.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    //Endpoint Post che salva un nuovo TierListElement o aggiorna uno esistente
    @PostMapping
    public ResponseEntity<TierListElement> createTierListElement(@RequestBody TierListElement tierListElement) {
        TierListElement savedElement = tierListElementService.saveTierListElement(tierListElement);
        return new ResponseEntity<>(savedElement, HttpStatus.CREATED);
    }

    
    //Endpoint Put che aggiorna un TierListElement
    @PutMapping("/{id}")
    public ResponseEntity<TierListElement> updateTierListElement(@PathVariable Long id, @RequestBody TierListElement tierListElement) {
        // Verifica se il TierListElement esiste prima di aggiornarlo
        if (tierListElementService.getTierListElementById(id).isPresent()) {
            tierListElement.setId(id); // Assicura che l'ID nel corpo corrisponda all'ID del percorso
            TierListElement updatedElement = tierListElementService.saveTierListElement(tierListElement);
            return new ResponseEntity<>(updatedElement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    //Endpoint Delete che elimina un TierListElement tramite il suo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTierListElement(@PathVariable Long id) {
        if (tierListElementService.getTierListElementById(id).isPresent()) {
            tierListElementService.deleteTierListElement(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Endpoint Get che recupera tutti i TierListElement appartenenti a una specifica TierList
    @GetMapping("/by-tierlist/{tierlistId}")
    public ResponseEntity<List<TierListElement>> getTierListElementsByTierlistId(@PathVariable Long tierlistId) {
        List<TierListElement> elements = tierListElementService.getTierListElementsByTierlistId(tierlistId);
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

  
    //Endpoint Get che recupera tutti i TierListElement che si riferiscono a un Element specifico.
    @GetMapping("/by-element/{elementId}")
    public ResponseEntity<List<TierListElement>> getTierListElementsByElementId(@PathVariable Long elementId) {
        List<TierListElement> elements = tierListElementService.getTierListElementsByElementId(elementId);
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

  
    //Endpoint Get che trova un TierListElement specifico per una data TierList e un dato Element.
    @GetMapping("/by-tierlist-and-element")
    public ResponseEntity<TierListElement> getTierListElementByTierlistAndElement(
            @RequestParam Long tierlistId,
            @RequestParam Long elementId) {
        Optional<TierListElement> element = tierListElementService.getTierListElementByTierlistAndElement(tierlistId, elementId);
        return element.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}