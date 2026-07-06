package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LanguageTrack;
import com.example.demo.service.LanguageService;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;
    public List<LanguageTrack> getAllLanguageTracks(){
        return languageService.getAllLanguageTracks();
    }
}
