package com.example.demo.service;

import com.example.demo.dto.SessionResultDto;
import com.example.demo.dto.StudySessionResponseDto;

import java.util.List;

public interface StudySessionService {

    StudySessionResponseDto completeSession(SessionResultDto dto);

    List<StudySessionResponseDto> getUserSessions(Long userId);
}