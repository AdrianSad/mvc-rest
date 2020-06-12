package com.adrian.springframework.services;

import com.adrian.springframework.domain.Category;
import com.adrian.springframework.exceptions.CategoryNotFoundException;
import com.adrian.springframework.repos.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Flux<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<ResponseEntity<Category>> getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException("Category name not found.")))
                .map(ResponseEntity::ok);
    }
}
