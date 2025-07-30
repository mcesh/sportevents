package com.example.sportsevents;

import com.example.sportsevents.model.ScoreUpdate;
import com.example.sportsevents.service.KafkaPublisherService;
import com.example.sportsevents.service.impl.KafkaPublisherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class KafkaPublisherServiceTest {

    private KafkaTemplate<String, Object> kafkaTemplate;
    private KafkaPublisherService kafkaPublisherService;

    @BeforeEach
    void setup() {
        kafkaTemplate = Mockito.mock(KafkaTemplate.class);
        kafkaPublisherService = new KafkaPublisherServiceImpl(kafkaTemplate);
    }

    @Test
    void testPublishScore() {
        ScoreUpdate update = new ScoreUpdate("event-123", "2:1");
        kafkaPublisherService.publishScore(update);
        verify(kafkaTemplate, times(1)).send(anyString(), eq("event-123"), eq(update));
    }
}
