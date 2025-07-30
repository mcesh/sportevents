package com.example.sportsevents.service.impl;

import com.example.sportsevents.model.ScoreUpdate;
import com.example.sportsevents.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {

    private final Random random = new Random();
    private final Map<String, int[]> scores = new ConcurrentHashMap<>();

    @Override
    public ScoreUpdate fetchScore(String eventId) {
        int[] score = scores.computeIfAbsent(eventId, id -> new int[] {0, 0});

        if (random.nextBoolean()) {
            score[0] += random.nextInt(2);
        } else {
            score[1] += random.nextInt(2);
        }

        String scoreText = score[0] + ":" + score[1];
        return new ScoreUpdate(eventId, scoreText);
    }
}
