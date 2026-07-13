package com.example.demo.service;

import java.util.ArrayList;
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
        StudyDeck studyDeck=studyDeckRepository.findById(id).orElse(()->{});
    }

}
