package com.example.TierList.service;

import com.example.TierList.model.TierElement;
import com.example.TierList.repository.TierListElementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TierElementService {

    private final TierListElementRepository tierListElementRepository;

    /**
     * Recupera tutti i TierListElement.
     * @return Una lista di tutti i TierListElement.
     */
    public List<TierElement> getAllTierListElements() {
        return tierListElementRepository.findAll();
    }

    /**
     * Recupera un TierListElement tramite il suo ID.
     * @param id L'ID del TierListElement.
     * @return Un Optional contenente il TierListElement se trovato.
     */
    public Optional<TierElement> getTierListElementById(Long id) {
        return tierListElementRepository.findById(id);
    }

    /**
     * Salva un nuovo TierListElement o aggiorna uno esistente.
     * @param tierListElement L'oggetto TierListElement da salvare/aggiornare.
     * @return Il TierListElement salvato/aggiornato.
     */
    public TierElement saveTierListElement(TierElement tierListElement) {
        return tierListElementRepository.save(tierListElement);
    }

    /**
     * Elimina un TierListElement tramite il suo ID.
     * @param id L'ID del TierListElement da eliminare.
     */
    public void deleteTierListElement(Long id) {
        tierListElementRepository.deleteById(id);
    }

    /**
     * Recupera tutti i TierListElement appartenenti a una specifica TierList.
     * @param tierlistId L'ID della TierList.
     * @return Una lista di TierListElement di quella TierList.
     */
    public List<TierElement> getTierListElementsByTierlistId(Long tierlistId) {
        return tierListElementRepository.findByTierlist_Id(tierlistId);
    }

    /**
     * Recupera tutti i TierListElement che si riferiscono a un Element specifico.
     * @param elementId L'ID dell'Element.
     * @return Una lista di TierListElement che usano quell'Element.
     */
    public List<TierElement> getTierListElementsByElementId(Long elementId) {
        return tierListElementRepository.findByElement_Id(elementId);
    }

    /**
     * Trova un TierListElement specifico per una data TierList e un dato Element.
     * @param tierlistId L'ID della TierList.
     * @param elementId L'ID dell'Element.
     * @return Un Optional contenente il TierListElement se trovato.
     */
    public Optional<TierElement> getTierListElementByTierlistAndElement(Long tierlistId, Long elementId) {
        return tierListElementRepository.findByTierlist_IdAndElement_Id(tierlistId, elementId);
    }
}