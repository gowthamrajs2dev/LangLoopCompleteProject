package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudySessionResponseDto {
    
}

/*
 session.getId(),
session.getStartTime(),
session.getEndTime(),
session.getScore(),
session.getUser().getId(),
session.getDeck().getId()
*/
/*

private Long id;
private SystemUser user;
@JoinColumn(name = "deck_id")
private StudyDeck deck;
private int score;  //include validation score should be in some range
private LocalDateTime startTime;
private LocalDateTime endTime;
*/