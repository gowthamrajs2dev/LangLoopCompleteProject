package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Flashcard;


public interface FlashcardRepository extends JpaRepository<Flashcard,Long>{
    List<Flashcard> findByDeckIdOrderByOrderIndexAsc(Long deckId);

    

}
