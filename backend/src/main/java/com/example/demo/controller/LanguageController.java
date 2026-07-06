package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LanguageTrack;
import com.example.demo.service.LanguageService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class LanguageController {
    @Autowired
    private LanguageService languageService;
    @GetMapping
    public List<LanguageTrack> getAllLanguageTracks(){
        return languageService.getAllLanguageTracks();
    }
    @PostMapping("/languages")
    public void addLanguageTrack(@RequestBody LanguageTrack languageTrack){
        languageService.addLanguageTrack(languageTrack);
    }
}
