package com.example.sportsevents.config;

import com.example.sportsevents.util.EventSchedulerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventSchedulerRegistryConfig {

    @Bean
    public EventSchedulerRegistry eventSchedulerRegistry() {
        return new EventSchedulerRegistry();
    }
}
