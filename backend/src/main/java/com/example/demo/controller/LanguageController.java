package com.example.demo.controller;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LanguageTrack;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    @Autowired
    private Service ser;
    public List<LanguageTrack> getLanguageTracks(){
        return ser.getLanguageTracks();
    }
}
