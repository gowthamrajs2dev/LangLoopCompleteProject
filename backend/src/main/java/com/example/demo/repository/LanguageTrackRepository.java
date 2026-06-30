package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LanguageTrack;

public interface LanguageTrackRepository extends JpaRepository<LanguageTrack,Long>{
    
}
