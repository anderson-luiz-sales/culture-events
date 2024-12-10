package com.culture.events.service.mapper;

import com.culture.events.dtos.response.EventResponseDTO;
import com.culture.events.entities.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EventResponseDTOMapper {

    public static List<EventResponseDTO> mapToEventResponseList(List<Event> events) {
        return events.stream()
                .map(EventResponseDTOMapper::mapToEventResponse)
                .collect(Collectors.toList());
    }

    public static EventResponseDTO mapToEventResponse(Event event) {
        return EventResponseDTO.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .description(event.getDescription())
                .eventDate(LocalDateTime.parse(event.getEventDate()))
                .location(event.getLocation())
                .category(event.getCategory())
                .build();
    }
}
