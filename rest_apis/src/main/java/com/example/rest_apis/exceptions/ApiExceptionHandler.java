package com.example.rest_apis.exceptions;

public class ApiExceptionHandler extends RuntimeException {

    public ApiExceptionHandler() {
        super();
    }

    public ApiExceptionHandler(String message){
        super(message);
    }
}
