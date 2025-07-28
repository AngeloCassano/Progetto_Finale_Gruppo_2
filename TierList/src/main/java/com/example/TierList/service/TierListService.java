package com.example.TierList.service;

import com.example.TierList.model.TierList;
import com.example.TierList.model.TierListElement;
import com.example.TierList.repository.TierListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TierListService {

private final TierListRepository tierListRepository;

public List<TierList> findAll() {
return tierListRepository.findAll();
}

public List<TierListRepository> findByUtenteId(Long utenteId) {
return tierListRepository.findByUtenteId(utenteId);
}

public List<TierListRepository> findByCategoryId(Long CategoryId) {
return tierListRepository.findByCatgoryId(CategoryId);
}

public TierList findById(Long id) {
return tierListRepository.findById(id).orElseThrow(() -> new RuntimeException("Tierlist non trovata"));
}

public TierList save(TierList tierList) {
return tierListRepository.save(tierList);
}

public void delete(Long id) {
tierListRepository.deleteById(id);
}
}