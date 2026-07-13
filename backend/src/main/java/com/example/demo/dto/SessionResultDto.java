package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionResultDto {
    private Long userId;
    private Long deckId;
    private int score;
    private int quality;
}
