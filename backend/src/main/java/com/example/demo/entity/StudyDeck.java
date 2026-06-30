package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class StudyDeck {
    @Id
    private long id;
    private String title;
    private String description;
    private DeskVisibility visibility;
}
