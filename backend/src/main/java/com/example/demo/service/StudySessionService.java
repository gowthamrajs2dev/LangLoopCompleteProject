package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SessionResultDto;

public interface StudySessionService {
    StudySessionResponseDto completeSession(SessionResultDto dto);
 
    List<StudySessionResponseDto> getUserSessions(Long userId);
} 