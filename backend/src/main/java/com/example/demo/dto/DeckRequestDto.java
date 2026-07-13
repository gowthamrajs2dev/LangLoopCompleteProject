package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeckRequestDto {
    @NotBlank
    private String title;
    private String description;
    @Min(1)
    private int capacity;
}
