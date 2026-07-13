package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DeckRequestDto {
    private String title;
    private String description;
    @Min(1)
    private int capacity;
}
