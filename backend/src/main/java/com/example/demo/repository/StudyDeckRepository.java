package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StudyDeck;

public interface StudyDeckRepository extends JpaRepository<StudyDeck,Long>{
    public List<StudyDeck> findByOwnerId(Long ownerId);
    public List<StudyDeck> findByTitleContaining(String keyword);
}
