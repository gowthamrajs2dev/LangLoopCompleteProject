package com.example.demo.repository;

import com.example.demo.entity.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {

    // Backs StudySessionService.getUserSessions(userId): full session
    // history for a learner, most recent first, for dashboard analytics.
    List<StudySession> findByUser_IdOrderByStartTimeDesc(Long userId);

    // Supports per-deck performance reporting.
    List<StudySession> findByDeck_Id(Long deckId);

    long countByUser_Id(Long userId);
}