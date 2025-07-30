package com.example.sportsevents.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

public class EventSchedulerRegistry {
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    public void register(String eventId, ScheduledFuture<?> future) {
        scheduledTasks.put(eventId, future);
    }

    public void cancel(String eventId) {
        ScheduledFuture<?> future = scheduledTasks.remove(eventId);
        if (future != null) {
            future.cancel(true);
        }
    }
}
