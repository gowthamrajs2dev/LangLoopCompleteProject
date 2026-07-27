package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StudySession;

public interface StudySessionRepository extends JpaRepository<StudySession,Long> {
    List<StudySession> findByUser_IdOrderByStartTimeDesc(Long userId);
    List<StudySession> findByDeck_Id(Long deckId);
    long countByUser_Id(Long userId);
}
