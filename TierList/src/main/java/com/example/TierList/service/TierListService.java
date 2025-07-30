package com.example.TierList.service;

import com.example.TierList.model.TierList;
import com.example.TierList.repository.TierListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TierListService {

    private final TierListRepository tierListRepository;  // Oggetto della repo per utilizzare i metodo specifici

    // Metodo per ottenere tutte le tierlist 
    public List<TierList> getAllTierLists() {
        return tierListRepository.findAll();
    }

    // Metodo per ottenere una tierlist tramite id
    public Optional<TierList> getTierListById(Long id) {
        return tierListRepository.findById(id);
    }

    // Metodo per salvare una tierlist
    public TierList saveTierList(TierList tierList) {
        return tierListRepository.save(tierList);
    }

    // Metodo per cancellare tramite id
    public void deleteTierList(Long id) {
        tierListRepository.deleteById(id);
    }

    // Metodo per ottenere una tier list tramite utente id 
    public List<TierList> getTierListsByUtenteId(Long utenteId) {
        return tierListRepository.findByUtente_Id(utenteId);
    }

    // Metodo per ottenere una tierlist tramite la categoria
    public List<TierList> getTierListsByCategoryId(Long categoryId) {
        return tierListRepository.findByCategory_Id(categoryId);
    }

    // Metodo per ottenere una tierlist tramite titolo
    public Optional<TierList> searchTierListByTitolo(String titolo) {
        return tierListRepository.findByTitoloContainingIgnoreCase(titolo);
    }

    

}