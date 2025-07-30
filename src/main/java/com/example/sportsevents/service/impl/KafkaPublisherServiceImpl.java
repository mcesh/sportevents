package com.example.sportsevents.service.impl;

import com.example.sportsevents.model.ScoreUpdate;
import com.example.sportsevents.service.KafkaPublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaPublisherServiceImpl implements KafkaPublisherService {

    private static final String TOPIC_NAME = "live-event-scores";

    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Override
    public void publishScore(ScoreUpdate update) {
        try {
            kafkaTemplate.send(TOPIC_NAME,update.getEventId(),update);
            log.info("Published score update: {}", update);
        }catch (Exception e){
            log.error("Failed to publish score update for event {}: {}", update.getEventId(), e.getMessage());
        }
    }
}
