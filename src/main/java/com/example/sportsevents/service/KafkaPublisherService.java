package com.example.sportsevents.service;

import com.example.sportsevents.model.ScoreUpdate;

public interface KafkaPublisherService {
    void publishScore(ScoreUpdate update);
}
