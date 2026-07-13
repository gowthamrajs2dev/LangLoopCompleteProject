package com.example.demo.dto;

import lombok.Data;

@Data
public class FlashcardRequestDto {
    private String frontContent;
    private String backContent;
    private int orderIndex;
}
