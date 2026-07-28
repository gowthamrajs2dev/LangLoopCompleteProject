package com.example.demo.repository;

import com.example.demo.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

    // Ordered retrieval required for sequential study/roadmap traversal.
    List<Flashcard> findByDeckIdOrderByOrderIndexAsc(Long deckId);

    // Explicit JPQL method, as required by the SRS, using the deck
    // relationship's id rather than a derived query method.
    @Query("SELECT f FROM Flashcard f WHERE f.deck.id = :deckId")
    List<Flashcard> findAllByDeckId(@Param("deckId") Long deckId);

    long countByDeckId(Long deckId);
}