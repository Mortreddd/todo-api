package com.example.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resoureNotFound(
            ResourceNotFoundException resourceNotFoundException,
            WebRequest request
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorDetails(new Date(), resourceNotFoundException.getMessage(), request.getDescription(false)));
    }


    @Data
    @AllArgsConstructor
    private static class ErrorDetails{
        private Date date;
        private String message;
        private String details;
    }
}
