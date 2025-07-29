package com.example.TierList.controller;

import com.example.TierList.model.TierListElement;
import com.example.TierList.service.TierListElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tierlist-elements")
@RequiredArgsConstructor
public class TierListElementController {

    private final TierListElementService tierListElementService;

    /**
     * Recupera tutti i TierListElement.
     * GET /api/tierlist-elements
     * @return Una lista di tutti i TierListElement.
     */
    @GetMapping
    public ResponseEntity<List<TierListElement>> getAllTierListElements() {
        List<TierListElement> elements = tierListElementService.getAllTierListElements();
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

    /**
     * Recupera un TierListElement tramite il suo ID.
     * GET /api/tierlist-elements/{id}
     * @param id L'ID del TierListElement.
     * @return Il TierListElement se trovato, altrimenti HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TierListElement> getTierListElementById(@PathVariable Long id) {
        Optional<TierListElement> element = tierListElementService.getTierListElementById(id);
        return element.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Salva un nuovo TierListElement o aggiorna uno esistente.
     * POST /api/tierlist-elements
     * @param tierListElement L'oggetto TierListElement da salvare/aggiornare.
     * @return Il TierListElement salvato/aggiornato.
     */
    @PostMapping
    public ResponseEntity<TierListElement> createTierListElement(@RequestBody TierListElement tierListElement) {
        TierListElement savedElement = tierListElementService.saveTierListElement(tierListElement);
        return new ResponseEntity<>(savedElement, HttpStatus.CREATED);
    }

    /**
     * Aggiorna un TierListElement esistente.
     * PUT /api/tierlist-elements/{id}
     * @param id L'ID del TierListElement da aggiornare.
     * @param tierListElement Il corpo del TierListElement aggiornato.
     * @return Il TierListElement aggiornato se trovato, altrimenti HttpStatus.NOT_FOUND.
     */
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

    /**
     * Elimina un TierListElement tramite il suo ID.
     * DELETE /api/tierlist-elements/{id}
     * @param id L'ID del TierListElement da eliminare.
     * @return HttpStatus.NO_CONTENT se eliminato con successo, altrimenti HttpStatus.NOT_FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTierListElement(@PathVariable Long id) {
        if (tierListElementService.getTierListElementById(id).isPresent()) {
            tierListElementService.deleteTierListElement(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Recupera tutti i TierListElement appartenenti a una specifica TierList.
     * GET /api/tierlist-elements/by-tierlist/{tierlistId}
     * @param tierlistId L'ID della TierList.
     * @return Una lista di TierListElement di quella TierList.
     */
    @GetMapping("/by-tierlist/{tierlistId}")
    public ResponseEntity<List<TierListElement>> getTierListElementsByTierlistId(@PathVariable Long tierlistId) {
        List<TierListElement> elements = tierListElementService.getTierListElementsByTierlistId(tierlistId);
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

    /**
     * Recupera tutti i TierListElement che si riferiscono a un Element specifico.
     * GET /api/tierlist-elements/by-element/{elementId}
     * @param elementId L'ID dell'Element.
     * @return Una lista di TierListElement che usano quell'Element.
     */
    @GetMapping("/by-element/{elementId}")
    public ResponseEntity<List<TierListElement>> getTierListElementsByElementId(@PathVariable Long elementId) {
        List<TierListElement> elements = tierListElementService.getTierListElementsByElementId(elementId);
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

    /**
     * Trova un TierListElement specifico per una data TierList e un dato Element.
     * GET /api/tierlist-elements/by-tierlist-and-element
     * @param tierlistId L'ID della TierList.
     * @param elementId L'ID dell'Element.
     * @return Un Optional contenente il TierListElement se trovato, altrimenti HttpStatus.NOT_FOUND.
     */
    @GetMapping("/by-tierlist-and-element")
    public ResponseEntity<TierListElement> getTierListElementByTierlistAndElement(
            @RequestParam Long tierlistId,
            @RequestParam Long elementId) {
        Optional<TierListElement> element = tierListElementService.getTierListElementByTierlistAndElement(tierlistId, elementId);
        return element.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}