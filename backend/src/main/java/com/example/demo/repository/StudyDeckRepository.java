package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StudyDeck;

public interface StudyDeckRepository extends JpaRepository<Long,StudyDeck>{
    
}
