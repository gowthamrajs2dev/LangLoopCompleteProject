package com.example.demo.repository;

import com.example.demo.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByDeck_IdOrderByOrderIndexAsc(Long deckId);

    @Query("SELECT f FROM Flashcard f WHERE f.deck.id = :deckId")
    List<Flashcard> findAllByDeckId(@Param("deckId") Long deckId);

    long countByDeck_Id(Long deckId);
}
