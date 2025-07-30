package com.example.sportsevents.service;

import com.example.sportsevents.model.ScoreUpdate;

public interface ExternalApiService {
    ScoreUpdate fetchScore(String eventId);
}
