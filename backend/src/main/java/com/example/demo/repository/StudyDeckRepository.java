package com.example.demo.repository;

import com.example.demo.entity.StudyDeck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyDeckRepository extends JpaRepository<StudyDeck, Long> {

    // Custom finder backing DeckList's search/filtering support.
    List<StudyDeck> findByTitleContainingIgnoreCase(String keyword);

    // Supports filtering roadmaps by the LINGUIST who created them.
    List<StudyDeck> findByOwnerId(Long ownerId);
}