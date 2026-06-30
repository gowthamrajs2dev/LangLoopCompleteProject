package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StudySession;

public interface StudySessionRepository extends JpaRepository<StudySession,Long> {
    
}
