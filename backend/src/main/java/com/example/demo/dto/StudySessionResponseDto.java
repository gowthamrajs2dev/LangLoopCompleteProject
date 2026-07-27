package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudySessionResponseDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer score;
    private Long userId;
    private Long deckId;
}
/*

private Long id;
private SystemUser user;
private StudyDeck deck;
private int score;  
private LocalDateTime startTime;
private LocalDateTime endTime;
*/