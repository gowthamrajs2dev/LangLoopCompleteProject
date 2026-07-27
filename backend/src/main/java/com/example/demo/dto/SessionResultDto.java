package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionResultDto {
    private Long userId;
    private Long deckId;
    private Integer score;  
    @NotNull
    @Min(0)
    @Max(5)
    private Integer quality;
}
