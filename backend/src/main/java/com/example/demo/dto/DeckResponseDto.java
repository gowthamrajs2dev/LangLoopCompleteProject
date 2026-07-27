package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Flashcard;
import com.example.demo.entity.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeckResponseDto {
    private Long id;
    private String title;
    private String description;
    private int capacity;
    private String mentorName;
    private Long ownerId;
}
//no constraints added because this dto is used to send response to client