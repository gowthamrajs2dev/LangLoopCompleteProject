package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LanguageTrack;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    public List<LanguageTrack> getLanguageTracks(){
        
    }
}
