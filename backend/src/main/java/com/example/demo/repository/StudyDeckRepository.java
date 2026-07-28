package com.example.demo.repository;

import com.example.demo.entity.StudyDeck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyDeckRepository extends JpaRepository<StudyDeck, Long> {
    List<StudyDeck> findByTitleContainingIgnoreCase(String keyword);
    List<StudyDeck> findByOwnerId(Long ownerId);
}