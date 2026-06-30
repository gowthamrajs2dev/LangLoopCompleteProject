package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Flashcard;
@Repository
public interface FlashcardRepository extends JpaRepository<Long,Flashcard>{
    
}
