package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.LanguageTrack;
import com.example.demo.repository.LanguageTrackRepository;

public class LanguageService {
    @Autowired
    LanguageTrackRepository repository;

    public List<LanguageTrack> getAlLanguageTracks(){
        
    }
}
