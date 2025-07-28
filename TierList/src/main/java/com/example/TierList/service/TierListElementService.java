package com.example.TierList.service;


import com.example.TierList.model.TierListElement;
import com.example.TierList.repository.TierListElementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TierListElementService {

private final TierListElementRepository tierListElementRepository;

public List<TierListElement> findAll() {
return tierListElementRepository.findAll();
}

public List<TierListElementRepository> findByElementId(Long ElementId) {
return tierListElementRepository.findByElementId(ElementId);
}

public List<TierListElementRepository> findByTierListId(Long tierListId) {
return tierListElementRepository.findByTierListId(tierListId);
}

public TierListElement findById(Long id) {
return tierListElementRepository.findById(id).orElseThrow(() -> new RuntimeException("Tierlistelement non trovata"));
}

public TierListElement save(TierListElement tierListElement) {
return tierListElementRepository.save(tierListElement);
}

public void delete(Long id) {
tierListElementRepository.deleteById(id);
}
}