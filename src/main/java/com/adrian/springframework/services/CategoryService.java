package com.adrian.springframework.services;

import com.adrian.springframework.domain.Category;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CategoryService {

    Flux<Category> getAllCategories();

    Mono<ResponseEntity<Category>> getCategoryByName(String name);
}
