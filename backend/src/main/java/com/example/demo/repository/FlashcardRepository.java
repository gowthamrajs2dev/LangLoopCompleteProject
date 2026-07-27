package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Flashcard;

public interface FlashcardRepository extends JpaRepository<Flashcard,Long>{
    //include a custome Query
}
