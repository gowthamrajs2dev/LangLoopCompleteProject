package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.entity.StudyDeck;
import com.example.demo.exception.StudyDeckNotFoundException;
import com.example.demo.repository.StudyDeckRepository;

@Service
public class DeckManagementService {
    @Autowired
    private StudyDeckRepository studyDeckRepository;

    public static DeckRequestDto convertToDeckRequestDto(StudyDeck studyDeck){
        DeckRequestDto dto=new DeckRequestDto();
        dto.setCapacity(studyDeck.getCapacity());
        dto.setDescription(studyDeck.getDescription());
        dto.setTitle(studyDeck.getTitle());
        return dto;
    }
    public List<DeckRequestDto> getAllDecks(){
        List<StudyDeck>li=studyDeckRepository.findAll();
        List<DeckRequestDto>result=new ArrayList<>();
        for(StudyDeck studyDeck : li){
            DeckRequestDto dto=convertToDeckRequestDto(studyDeck);
            result.add(dto);
        }
        return result;
    }


    public DeckRequestDto getDeckById(Long id){
        StudyDeck studyDeck=studyDeckRepository.findById(id).orElseThrow(()->new StudyDeckNotFoundException("id not found"));
        return convertToDeckRequestDto(studyDeck);
    }

    public void createDeck(DeckRequestDto dto){
        StudyDeck studyDeck=new StudyDeck();
        studyDeck.setTitle(dto.getTitle());
        studyDeck.setDescription(dto.getDescription());
        studyDeck.setCapacity(dto.getCapacity());
        // studyDeck.setMentorName();
        // studyDeck.setOwnerId();
        // studyDeck.setSystemUser();
        studyDeckRepository.save(studyDeck);
    }

    public void deleteDeck(Long id){
        studyDeckRepository.deleteById(id);
    }

    public void updateDeck(Long id,DeckRequestDto dto){
        StudyDeck studyDeck=studyDeckRepository.findById(id)
        .orElseThrow(()->new StudyDeckNotFoundException("no id found for updating study deck"));
        studyDeck.setTitle(dto.getTitle())
    }
}
