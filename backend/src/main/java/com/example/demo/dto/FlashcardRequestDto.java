package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlashcardRequestDto {
    private String frontContent;
    private String backContent;
    private int orderIndex;
}
