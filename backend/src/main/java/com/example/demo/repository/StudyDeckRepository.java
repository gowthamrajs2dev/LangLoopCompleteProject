package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StudyDeck;

public interface StudyDeckRepository extends JpaRepository<StudyDeck,Long>{
    
    List<StudyDeck> findByTitleContaining(String keyword);
    StudyDeck findByOwnerId(long ownerId);
}
