package com.example.TierList.service;

import com.example.TierList.model.Image;
import com.example.TierList.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> getAllElements() {
        return imageRepository.findAll();
    }


    public Optional<Image> getElementById(Long id) {
        return imageRepository.findById(id);
    }

   
    public Image saveElement(Image element) {
        return imageRepository.save(element);
    }


    public void deleteElement(Long id) {
        imageRepository.deleteById(id);
    }

   
    public List<Image> searchElementsByName(String name) {
        return imageRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Image> getElementsByCategoryId(Long categoryId) {
        return imageRepository.findByCategoryId(categoryId);
    }

 
    public Optional<Image> getElementByNameAndCategoryId(String name, Long categoryId) {
        return imageRepository.findByNameAndCategoryId(name, categoryId);
    }
}