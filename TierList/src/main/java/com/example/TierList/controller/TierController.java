package com.example.TierList.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TierList.model.Tier;
import com.example.TierList.service.TierService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tiers")
@RequiredArgsConstructor
public class TierController {

    private final TierService TierService;

    /**
     * Recupera tutti i Tier.
     * GET /api/tierlist-elements
     * @return Una lista di tutti i Tier.
     */
    @GetMapping
    public ResponseEntity<List<Tier>> getAllTiers() {
        List<Tier> elements = TierService.getAllTiers();
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

    /**
     * Recupera un Tier tramite il suo ID.
     * GET /api/tierlist-elements/{id}
     * @param id L'ID del Tier.
     * @return Il Tier se trovato, altrimenti HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tier> getTierById(@PathVariable Long id) {
        Optional<Tier> element = TierService.getTierById(id);
        return element.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Salva un nuovo Tier o aggiorna uno esistente.
     * POST /api/tierlist-elements
     * @param Tier L'oggetto Tier da salvare/aggiornare.
     * @return Il Tier salvato/aggiornato.
     */
    @PostMapping
    public ResponseEntity<Tier> createTier(@RequestBody Tier Tier) {
        Tier savedElement = TierService.saveTier(Tier);
        return new ResponseEntity<>(savedElement, HttpStatus.CREATED);
    }

    /**
     * Aggiorna un Tier esistente.
     * PUT /api/tierlist-elements/{id}
     * @param id L'ID del Tier da aggiornare.
     * @param Tier Il corpo del Tier aggiornato.
     * @return Il Tier aggiornato se trovato, altrimenti HttpStatus.NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tier> updateTier(@PathVariable Long id, @RequestBody Tier Tier) {
        // Verifica se il Tier esiste prima di aggiornarlo
        if (TierService.getTierById(id).isPresent()) {
            Tier.setId(id); // Assicura che l'ID nel corpo corrisponda all'ID del percorso
            Tier updatedElement = TierService.saveTier(Tier);
            return new ResponseEntity<>(updatedElement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un Tier tramite il suo ID.
     * DELETE /api/tierlist-elements/{id}
     * @param id L'ID del Tier da eliminare.
     * @return HttpStatus.NO_CONTENT se eliminato con successo, altrimenti HttpStatus.NOT_FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTier(@PathVariable Long id) {
        if (TierService.getTierById(id).isPresent()) {
            TierService.deleteTier(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}