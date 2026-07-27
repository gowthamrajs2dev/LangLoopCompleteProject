package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.FlashcardRepository;
import com.example.demo.repository.RetentionMetricRepository;
import com.example.demo.repository.SystemUserRepository;

import jakarta.transaction.Transactional;

public class SpacedRepetitionServiceImpl implements SpacedRepetitionService {
    private static final double MIN_EASE_FACTOR = 1.3;
    private static final int MASTERED_INTERVAL_THRESHOLD_DAYS = 21;
 
    @Autowired
    private RetentionMetricRepository retensionMetricRepository;retentionMetricRepository
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private FlashcardRepository flashcardRepository;

    @Override
    @Transactional 
    public void updateRetentionMetric(Long userId,Long cardId,Integer quality){
        if(quality == null || quality <0 || quality>5){
            throw new IllegalArgumentException("Quality must be between 0 and 5");
        }

        RetentionMetricRepository metric = retensionMetricRepository
                    .findByUser_IdAndFlashcard_Id(userId,cardId)
                    .orElseGet(()->createNewMetric(userId,cardId));
        double updatedEase = metric.getEaseFactor()+(0.1)

    }

}
