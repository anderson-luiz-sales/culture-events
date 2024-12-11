package com.culture.events.factory;

import com.culture.events.dtos.response.EventResponseDTO;

import java.time.LocalDateTime;

public class EventResponseDTOFactory {

    public static EventResponseDTO makeEventResponseDTO() {
        return EventResponseDTO.builder()
                .id(1L)
                .eventName("Concerto de Música")
                .description("Um concerto de música clássica ao ar livre.")
                .eventDate(LocalDateTime.of(2024, 5, 10, 20, 0))
                .location("Central Park")
                .category("Música")
                .build();
    }
}
