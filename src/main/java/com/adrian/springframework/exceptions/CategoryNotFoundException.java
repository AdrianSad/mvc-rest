package com.adrian.springframework.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message){
        super(message);
    }
}
