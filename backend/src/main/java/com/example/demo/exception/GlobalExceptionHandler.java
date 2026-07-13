package com.example.demo.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudyDeckNotFoundException.class)
    public ResponseEntity<String> printError(StudyDeckNotFoundException ex){
        return ResponseEntity.status(HttpStatusCode.)
    }
}
