package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LanguageTrack;
import com.example.demo.repository.LanguageTrackRepository;

@Service
public class LanguageService {
    @Autowired
    LanguageTrackRepository languageTrackRepository;

    public List<LanguageTrack> getAlLanguageTracks(){

    }
}
