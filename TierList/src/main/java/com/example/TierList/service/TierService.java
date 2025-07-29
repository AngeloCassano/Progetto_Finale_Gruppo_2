package com.example.TierList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.TierList.model.Tier;
import com.example.TierList.repository.TierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TierService {

    private final TierRepository TierRepository;

    /**
      Recupera tutti i Tier. (posizione elementi)
      @return Una lista di tutti i Tier.
     */
    public List<Tier> getAllTiers() {
        return TierRepository.findAll();
    }

    /**
      Recupera un Tier tramite il suo ID.
      @param id L'ID del Tier.
      @return Un Optional contenente il Tier se trovato.
     */
    public Optional<Tier> getTierById(Long id) {
        return TierRepository.findById(id);
    }

    /**
      Salva un nuovo Tier o aggiorna uno esistente.
      @param Tier L'oggetto Tier da salvare/aggiornare.
      @return Il Tier salvato/aggiornato.
     */
    public Tier saveTier(Tier Tier) {
        return TierRepository.save(Tier);
    }

    /**
      Elimina un Tier tramite il suo ID.
      @param id L'ID del Tier da eliminare.
     */
    public void deleteTier(Long id) {
        TierRepository.deleteById(id);
    }

    /**
      Recupera tutti i Tier appartenenti a una specifica TierList.
      @param tierlistId L'ID della TierList.
      @return Una lista di Tier di quella TierList.
     */
    public List<Tier> getTiersByTierlistId(Long tierlistId) {
        return TierRepository.findByTierlist_Id(tierlistId);
    }
}