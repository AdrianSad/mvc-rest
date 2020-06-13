package com.adrian.springframework.services;

import com.adrian.springframework.domain.Category;
import com.adrian.springframework.exceptions.ErrorResponse;
import com.adrian.springframework.exceptions.NotFoundException;
import com.adrian.springframework.repos.CategoryRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
                .switchIfEmpty(Mono.error(new NotFoundException("Category name not found.")))
                .map(ResponseEntity::ok);
    }
}
