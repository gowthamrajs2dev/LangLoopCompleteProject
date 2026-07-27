package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SessionResultDto;
import com.example.demo.service.StudySessionService;

@RestController
@RequestMapping("/api/study")
public class StudyController {

    @Autowired
    StudySessionService studySessionService;
    @GetMapping("/due")
    public List<StudySessionResponseDto> getUserSession(Long userId){
        return studySessionService.getUserSession(userId);
    }
    @PostMapping("/complete")
    public StudySessionResponseDto completeSession(SessionResultDto dto){

    }
}   
