package com.culture.events.service.mapper;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.dtos.request.EventRequestListDTO;
import com.culture.events.entities.Event;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMapper {

    public static List<Event> mapToEventList(EventRequestListDTO eventRequestList) {
        return eventRequestList.getEvents().stream()
                .map(EventMapper::mapToEvent)
                .collect(Collectors.toList());
    }

    public static Event mapToEvent(EventRequestDTO eventRequest) {
        return Event.builder()
                .eventName(eventRequest.getEventName())
                .description(eventRequest.getDescription())
                .eventDate(eventRequest.getEventDate())
                .location(eventRequest.getLocation())
                .category(eventRequest.getCategory())
                .build();
    }
}
