package com.culture.events.service.mapper;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.entities.Event;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMapper {

    public static Event mapToEvent(EventRequestDTO eventRequest) {
        return Event.builder()
                .eventName(eventRequest.getEventName())
                .description(eventRequest.getDescription())
                .eventDate(eventRequest.getEventDate())
                .location(eventRequest.getLocation())
                .category(eventRequest.getCategory())
                .build();
    }

    public static void updateEventFromRequest(Event event, EventRequestDTO eventRequest) {
        event.setEventName(eventRequest.getEventName());
        event.setDescription(eventRequest.getDescription());
        event.setEventDate(eventRequest.getEventDate());
        event.setLocation(eventRequest.getLocation());
        event.setCategory(eventRequest.getCategory());
    }
}
