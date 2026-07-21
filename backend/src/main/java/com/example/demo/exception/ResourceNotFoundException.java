package com.example.demo.exception;


public class ResourceNotFoundException extends RuntimeException{
    ResourceNotFoundException(String message){
        super(message);
    }
}
