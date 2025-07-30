package com.example.sportsevents.service;

import com.example.sportsevents.model.ScoreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaScoreUpdateConsumer {

    @KafkaListener(topics = "live-event-scores", groupId = "score-group")
    public void consume(ScoreUpdate update) {
        log.info("Consumed score update: {}", update);
    }
}
