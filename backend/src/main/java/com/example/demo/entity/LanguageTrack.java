package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LanguageTrack {
    @Id
    private long id;
    private String name;
    private String isoCode;
    private DifficultyLevel difficultyLevel;
    private String description;
    private LocalDate
}
