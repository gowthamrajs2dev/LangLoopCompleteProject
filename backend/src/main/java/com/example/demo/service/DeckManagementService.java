package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.entity.StudyDeck;
import com.example.demo.repository.StudyDeckRepository;

@Service
public class DeckManagementService {
    @Autowired
    private StudyDeckRepository studyDeckRepository;
    List<DeckRequestDto> getAllDecks(){
        studyDeck
    }
}
