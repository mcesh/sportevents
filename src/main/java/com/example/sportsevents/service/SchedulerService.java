package com.example.sportsevents.service;

public interface SchedulerService {
    void scheduleEvent(String eventId);
    void cancelEvent(String eventId);
}
