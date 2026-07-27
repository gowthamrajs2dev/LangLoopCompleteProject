package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DueReviewResponseDto {
    private Long cardId;
    private String frontContent;
    private String backContent;
    private LocalDateTime nextReviewDate;
    private String masteryLevel;
}
