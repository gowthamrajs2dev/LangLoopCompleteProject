package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DeckMangementServiceImpl;

@RestController
@RequestMapping("/api")
public class DeckController {
    @Autowired
    DeckMangementServiceImpl deckMangementServiceImpl;

    @GetMapping("/decks")
    
}
