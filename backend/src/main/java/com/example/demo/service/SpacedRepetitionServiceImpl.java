package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.FlashcardRepository;
import com.example.demo.repository.RetensionMetricRepository;
import com.example.demo.repository.SystemUserRepository;

public class SpacedRepetitionServiceImpl implements SpacedRepetitionService {
    private static final double MIN_EASE_FACTOR = 1.3;
    private static final int MASTERED_INTERVAL_THRESHOLD_DAYS = 21;
 
    @Autowired
    private RetensionMetricRepository retensionMetricRepository;
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private FlashcardRepository flashcardRepository;


}
