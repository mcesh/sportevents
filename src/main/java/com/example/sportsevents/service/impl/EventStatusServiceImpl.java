package com.example.sportsevents.service.impl;

import com.example.sportsevents.model.EventState;
import com.example.sportsevents.model.EventStatusRequest;
import com.example.sportsevents.service.EventStatusService;
import com.example.sportsevents.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class EventStatusServiceImpl implements EventStatusService {

    private final Map<String, EventState> eventStatusMap = new ConcurrentHashMap<>();
    private final SchedulerService schedulerService;

    @Override
    public void updateEventStatus(EventStatusRequest request) {
        eventStatusMap.put(request.getEventId(),request.getStatus());
        if (request.getStatus() == EventState.LIVE){
            schedulerService.scheduleEvent(request.getEventId());
        }else {
            schedulerService.cancelEvent(request.getEventId());
        }

    }
}
