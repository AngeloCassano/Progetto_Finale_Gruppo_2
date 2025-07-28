package com.example.TierList.service;

import com.example.TierList.model.TierList;
import com.example.TierList.model.Utente;
import com.example.TierList.repository.TierListRepository;
import com.example.TierList.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TierListService {

    private final TierListRepository tierListRepository;
    private final UtenteRepository utenteRepository;

    /**
     * Recupera tutte le TierList.
     * @return Una lista di tutte le TierList.
     */
    public List<TierList> getAllTierLists() {
        return tierListRepository.findAll();
    }

    /**
     * Recupera una TierList tramite il suo ID.
     * @param id L'ID della TierList.
     * @return Un Optional contenente la TierList se trovata.
     */
    public Optional<TierList> getTierListById(Long id) {
        return tierListRepository.findById(id);
    }

    //creates or updates a new tier list
    public TierList saveTierList(TierList tierList)
    {
        return tierListRepository.save(tierList);
    }

    /**
     * Elimina una TierList tramite il suo ID.
     * @param id L'ID della TierList da eliminare.
     */
    public void deleteTierList(Long id) {
        tierListRepository.deleteById(id);
    }

    /**
     * Recupera tutte le TierList create da un utente specifico.
     * @param utenteId L'ID dell'utente.
     * @return Una lista di TierList create dall'utente specificato.
     */
    public List<TierList> getTierListsByUtenteId(Long utenteId) {
        return tierListRepository.findByUtente_Id(utenteId);
    }

    /**
     * Recupera tutte le TierList associate a una specifica categoria.
     * @param categoryId L'ID della categoria.
     * @return Una lista di TierList associate alla categoria specificata.
     */
    public List<TierList> getTierListsByCategoryId(Long categoryId) {
        return tierListRepository.findByCategory_Id(categoryId);
    }

    /**
     * Cerca TierList per titolo (case-insensitive).
     * @param titolo Il titolo da cercare.
     * @return Un Optional contenente la TierList se trovata.
     */
    public Optional<TierList> searchTierListByTitolo(String titolo) {
        return tierListRepository.findByTitoloContainingIgnoreCase(titolo);
    }

    /**
     * Recupera le TierList di un utente specifico appartenenti a una certa categoria.
     * @param utenteId L'ID dell'utente.
     * @param categoryId L'ID della categoria.
     * @return Una lista di TierList che corrispondono ai criteri.
     */
    public List<TierList> getTierListsByUtenteAndCategory(Long utenteId, Long categoryId) {
        return tierListRepository.findByUtente_IdAndCategory_Id(utenteId, categoryId);
    }
}