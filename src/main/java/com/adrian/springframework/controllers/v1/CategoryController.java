package com.adrian.springframework.controllers.v1;

import com.adrian.springframework.config.SwaggerConfig;
import com.adrian.springframework.domain.Category;
import com.adrian.springframework.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api(tags = {SwaggerConfig.CATEGORY_CONTROLLER_TAG})
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories/";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "List of categories.", notes = "This will get u every category in the list.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @ApiOperation(value = "Get category by name.", notes = "You will receive category if exists.")
    @GetMapping("{name}")
    public Mono<ResponseEntity<Category>> getCategoryByName(@PathVariable @ApiParam(value = "Name of category to return ") String name){
        return categoryService.getCategoryByName(name);
    }
}
