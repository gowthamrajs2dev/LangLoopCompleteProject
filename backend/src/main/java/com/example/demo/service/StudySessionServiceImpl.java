package com.example.demo.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudySessionServiceImpl implements StudySessionService {

    private final StudySessionRepository studySessionRepository;
    private final SystemUserRepository systemUserRepository;
    private final StudyDeckRepository studyDeckRepository;
    private final FlashcardRepository flashcardRepository;
    private final SpacedRepetitionService spacedRepetitionService;

    public StudySessionServiceImpl(StudySessionRepository studySessionRepository,
                                    SystemUserRepository systemUserRepository,
                                    StudyDeckRepository studyDeckRepository,
                                    FlashcardRepository flashcardRepository,
                                    SpacedRepetitionService spacedRepetitionService) {
        this.studySessionRepository = studySessionRepository;
        this.systemUserRepository = systemUserRepository;
        this.studyDeckRepository = studyDeckRepository;
        this.flashcardRepository = flashcardRepository;
        this.spacedRepetitionService = spacedRepetitionService;
    }

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

        // SessionResultDto carries a single quality rating for the whole
        // deck session (not per card), so it's applied to every flashcard
        // in the deck. This is an interpretation of "updates retention
        // metrics automatically after each completed session" -- the SRS
        // doesn't specify per-card quality capture during a session.
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