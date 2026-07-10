package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LanguageTrack;
import com.example.demo.service.LanguageService;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;
    @GetMapping
    public List<LanguageTrack> getAllLanguageTracks(){
        return languageService.getAllLanguageTracks();
    }
    @PostMapping
    public void addLanguageTrack(@RequestBody LanguageTrack languageTrack){
        languageService.addLanguageTrack(languageTrack);
    }

    @PostMapping("/{trackId}/assign/{linguistId")
    public void assignLinguist()
}
