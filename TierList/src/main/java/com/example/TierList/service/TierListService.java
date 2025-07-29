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

    private final TierListRepository tierListRepository;

    
    public List<TierList> getAllTierLists() {
        return tierListRepository.findAll();
    }

   
    public Optional<TierList> getTierListById(Long id) {
        return tierListRepository.findById(id);
    }

  
    public TierList saveTierList(TierList tierList) {
        return tierListRepository.save(tierList);
    }

   
    public void deleteTierList(Long id) {
        tierListRepository.deleteById(id);
    }

 
    public List<TierList> getTierListsByUtenteId(Long utenteId) {
        return tierListRepository.findByUtente_Id(utenteId);
    }

    
    public List<TierList> getTierListsByCategoryId(Long categoryId) {
        return tierListRepository.findByCategory_Id(categoryId);
    }


    public Optional<TierList> searchTierListByTitolo(String titolo) {
        return tierListRepository.findByTitoloContainingIgnoreCase(titolo);
    }

  
    public List<TierList> getTierListsByUtenteAndCategory(Long utenteId, Long categoryId) {
        return tierListRepository.findByUtente_IdAndCategory_Id(utenteId, categoryId);
    }
}