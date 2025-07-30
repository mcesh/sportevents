package com.example.sportsevents.controller;

import com.example.sportsevents.model.EventStatusRequest;
import com.example.sportsevents.service.EventStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventStatusController {

    private final EventStatusService eventStatusService;

    @PostMapping("/status")
    public ResponseEntity<String> updateStatus(@RequestBody EventStatusRequest request) {
        eventStatusService.updateEventStatus(request);
        return ResponseEntity.ok("Event status updated");
    }
}
