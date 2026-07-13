package com.example.demo.exception;

public class StudyDeckNotFoundException extends RuntimeException{
    public StudyDeckNotFoundException(String message){
        super(message);
    }
}
