package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashcardResponseDto {
    private Long id;
    private String frontContent;
    private String backContent;
    private Integer orderIndex;
    private Long deckId;
}
