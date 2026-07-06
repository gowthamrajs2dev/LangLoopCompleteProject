package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudyDeck;
import com.example.demo.repository.StudyDeckRepository;

@Service
public class DeckManagementService {
    @Autowired
    private StudyDeckRepository studyDeckRepository;
    public List<StudyDeck>
}
