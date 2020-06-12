package com.adrian.springframework.controllers.v1;

import com.adrian.springframework.domain.Category;
import com.adrian.springframework.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories/";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("{name}")
    public Mono<ResponseEntity<Category>> getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }
}
