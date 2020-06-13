package com.adrian.springframework.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String message;

    public ErrorResponse(String s) {
        this.message = s;
    }
}
