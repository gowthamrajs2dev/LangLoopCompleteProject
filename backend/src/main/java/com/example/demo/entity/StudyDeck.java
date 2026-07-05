package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class StudyDeck {
    @Id
    private long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private DeskVisibility visibility;
    private int flashcardCount;
    private LocalDateTime creationTime;
    private LocalDateTime modifiedTime;
    @ManyToOne
    @JoinColumn(name = "system_user")
    private SystemUser systemUser;
}
