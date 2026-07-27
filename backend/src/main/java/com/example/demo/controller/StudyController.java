package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SessionResultDto;

@RestController
@RequestMapping("/api/study")
public class StudyController {
    
    @GetMapping("/due")
    public List<StudySessionResponseDto> getUserSession(Long userId){
        
    }
    @PostMapping("/complete")
    public StudySessionResponseDto completeSession(SessionResultDto dto){

    }
}   
