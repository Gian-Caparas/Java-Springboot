package com.example.firstspringbootapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.firstspringbootapp.service.NameErrorResponse;
import com.example.firstspringbootapp.service.NameNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<NameErrorResponse> handleNotFound(NameNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<NameErrorResponse> handleException(Exception ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<NameErrorResponse> buildResponse(String message, HttpStatus status) {
        NameErrorResponse error = new NameErrorResponse();
        error.setStatus(status.value());
        error.setMessage(message);
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, status);
    }
}
