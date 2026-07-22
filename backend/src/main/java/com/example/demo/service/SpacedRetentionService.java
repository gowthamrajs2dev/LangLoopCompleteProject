package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RetentionMetric;
import com.example.demo.repository.RetensionMetricRepository;

@Service
public class SpacedRetentionService {

    @Autowired
    private RetensionMetricRepository retensionMetricRepository;
    public void updateRetentionMetric(Long userId,Long cardId,int quality)
    {
        
    }
}
