package com.culture.events.factory;

import com.culture.events.dtos.request.EventRequestDTO;

import java.time.LocalDateTime;

public class EventRequestDTOFactory {

    public static EventRequestDTO makeEventRequestDTO() {
        return new EventRequestDTO().toBuilder()
                .eventName("Concerto de Música")
                .description("Um concerto de música clássica ao ar livre.")
                .eventDate(LocalDateTime.of(2024, 5, 10, 20, 0))
                .location("Central Park")
                .category("Música")
                .build();
    }
}
