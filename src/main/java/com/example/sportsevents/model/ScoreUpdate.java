package com.example.sportsevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreUpdate {
    private String eventId;
    private String currentScore;
}
