package com.example.sportsevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventStatusRequest {
    private String eventId;
    private EventState status;
}
