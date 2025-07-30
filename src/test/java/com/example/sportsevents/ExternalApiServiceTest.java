package com.example.sportsevents;

import com.example.sportsevents.model.ScoreUpdate;
import com.example.sportsevents.service.ExternalApiService;
import com.example.sportsevents.service.impl.ExternalApiServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExternalApiServiceTest {

    private ExternalApiService externalApiService;

    @BeforeEach
    void setup() {
        externalApiService = new ExternalApiServiceImpl();
    }

    @Test
    void testFetchScoreReturnsConsistentFormat() {
        ScoreUpdate update = externalApiService.fetchScore("match-123");
        assertNotNull(update);
        assertEquals("match-123", update.getEventId());
        assertTrue(update.getCurrentScore().matches("\\d+:\\d+"));
    }
}
