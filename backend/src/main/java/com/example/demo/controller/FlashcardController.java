package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Flashcard;

@RestController
@RequestMapping("/api/decks")
public class FlashcardController {
    
    @GetMapping("/{id}/cards")
    public List<Flashcard>
}
