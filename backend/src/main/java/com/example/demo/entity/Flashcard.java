package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Flashcard {
    @Id
    private long id;
    private String frontSideContent;
    private String ;
}
