package com.example.sportsevents;

import com.example.sportsevents.model.ScoreUpdate;
import com.example.sportsevents.service.ExternalApiService;
import com.example.sportsevents.service.KafkaPublisherService;
import com.example.sportsevents.service.SchedulerService;
import com.example.sportsevents.util.EventSchedulerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.Mockito.*;

public class SchedulerServiceTest {

    private ExternalApiService externalApiService;
    private KafkaPublisherService kafkaPublisherService;
    private EventSchedulerRegistry registry;
    private SchedulerService schedulerService;

    @BeforeEach
    void setup() {
        externalApiService = mock(ExternalApiService.class);
        kafkaPublisherService = mock(KafkaPublisherService.class);
        registry = new EventSchedulerRegistry();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        schedulerService = new com.example.sportsevents.service.impl.SchedulerServiceImpl(executorService, registry, externalApiService, kafkaPublisherService);
    }

    @Test
    void testScheduleEvent() throws InterruptedException {
        when(externalApiService.fetchScore("event-1")).thenReturn(new ScoreUpdate("event-1", "1:0"));
        schedulerService.scheduleEvent("event-1");
        Thread.sleep(1500); // Wait for task to trigger
        verify(externalApiService, atLeastOnce()).fetchScore("event-1");
        verify(kafkaPublisherService, atLeastOnce()).publishScore(any());
    }

    @Test
    void testCancelEvent() {
        schedulerService.scheduleEvent("event-1");
        schedulerService.cancelEvent("event-1");
    }
}
