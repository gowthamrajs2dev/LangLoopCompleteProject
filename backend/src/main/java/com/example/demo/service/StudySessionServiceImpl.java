package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SessionResultDto;
import com.example.demo.dto.StudySessionResponseDto;
import com.example.demo.entity.Flashcard;
import com.example.demo.entity.StudyDeck;
import com.example.demo.entity.StudySession;
import com.example.demo.entity.SystemUser;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FlashcardRepository;
import com.example.demo.repository.StudyDeckRepository;
import com.example.demo.repository.StudySessionRepository;
import com.example.demo.repository.SystemUserRepository;

import jakarta.transaction.Transactional;

@Service
public class StudySessionServiceImpl implements StudySessionService{
    @Autowired
    private StudySessionRepository studySessionRepository;
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private StudyDeckRepository studyDeckRepository;
    @Autowired
    private FlashcardRepository flashcardRepository;
    @Autowired
    private SpacedRepetitionService spacedRepetitionService;

    @Override
    @Transactional
    public StudySessionResponseDto completeSession(SessionResultDto dto) {
        SystemUser user = systemUserRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SystemUser not found with id: " + dto.getUserId()));
        StudyDeck deck = studyDeckRepository.findById(dto.getDeckId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "StudyDeck not found with id: " + dto.getDeckId()));
 
        LocalDateTime now = LocalDateTime.now();
        StudySession session = new StudySession(now, now, dto.getScore(), user, deck);
        StudySession saved = studySessionRepository.save(session);
        
        List<Flashcard> cardsInDeck = flashcardRepository.findByDeckIdOrderByOrderIndexAsc(dto.getDeckId());
        for (Flashcard card : cardsInDeck) {
            spacedRepetitionService.updateRetentionMetric(dto.getUserId(), card.getId(), dto.getQuality());
        }
 
        return toResponseDto(saved);
    }
 
    @Override
    public List<StudySessionResponseDto> getUserSessions(Long userId) {
        return studySessionRepository.findByUser_IdOrderByStartTimeDesc(userId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
 
    private StudySessionResponseDto toResponseDto(StudySession session) {
        return new StudySessionResponseDto(
                session.getId(),
                session.getStartTime(),
                session.getEndTime(),
                session.getScore(),
                session.getUser().getId(),
                session.getDeck().getId()
        );
    }
}
