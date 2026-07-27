package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/study")
public class StudyController {
    @GetMapping("")
    public List<StudySessionResponseDto> getUserSession(Long userId){
        
    }

    public 
}   
