package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Flashcard;


public interface FlashcardRepository extends JpaRepository<Flashcard,Long>{
    List<Flashcard> findByDeckIdOrderByOrderIndexAsc(Long deckId);

    @Query("SELECT f FROM Flashcard f WHERE f.deck.id=:deckId")
    List<Flashcard>findAllByDeckId(@Param("deckId") Long deckId);

    long countByDeckId(Long deckId);
}
