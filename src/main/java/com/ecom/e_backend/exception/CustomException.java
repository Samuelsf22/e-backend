package com.ecom.e_backend.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    private HttpStatus httpStatus;

    public CustomException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
}
