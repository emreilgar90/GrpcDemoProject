package com.emreilgar.productservice.service;

import com.emreilgar.productservice.model.Category;
import com.emreilgar.productservice.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ICategoryRepository repository;

    public Category add(Category category) {
        return repository.save(category);
    }
}
