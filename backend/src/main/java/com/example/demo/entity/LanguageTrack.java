package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LanguageTrack {
    @Id
    private long id;
    private String name;
    private String isoCode;
    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;
    private String description;
    private LocalDateTime modifiedTime;

}
