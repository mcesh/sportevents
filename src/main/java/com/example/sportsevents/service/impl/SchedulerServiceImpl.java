package com.example.sportsevents.service.impl;

import com.example.sportsevents.model.ScoreUpdate;
import com.example.sportsevents.service.ExternalApiService;
import com.example.sportsevents.service.KafkaPublisherService;
import com.example.sportsevents.service.SchedulerService;
import com.example.sportsevents.util.EventSchedulerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final ScheduledExecutorService executorService;
    private final EventSchedulerRegistry registry;
    private final ExternalApiService externalApiService;
    private final KafkaPublisherService kafkaPublisherService;

    @Override
    public void scheduleEvent(String eventId) {
        ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(() -> {
            try {
                ScoreUpdate scoreUpdate = externalApiService.fetchScore(eventId);
                kafkaPublisherService.publishScore(scoreUpdate);
            } catch (Exception e) {
                System.out.println("Error in scheduled task for event " + eventId + ": " + e.getMessage());
            }
        }, 0, 10, TimeUnit.SECONDS);
        registry.register(eventId, scheduledFuture);
    }

    @Override
    public void cancelEvent(String eventId) {
        registry.cancel(eventId);
    }
}
