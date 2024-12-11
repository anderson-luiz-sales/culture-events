package com.culture.events.factory;

import com.culture.events.entities.Event;

import java.time.LocalDateTime;

public class EventFactory {

    public static Event makeEventFirst() {
        return Event.builder()
                .id(1L)
                .eventName("Concerto de Música")
                .description("Um concerto de música clássica ao ar livre.")
                .eventDate(LocalDateTime.of(2024, 5, 10, 20, 0))
                .location("Central Park")
                .category("Música")
                .build();
    }

    public static Event makeEventSecond() {
        return Event.builder()
                .id(2L)
                .eventName("Show de dança")
                .description("Descrição 2")
                .eventDate(LocalDateTime.of(2024, 6, 10, 20, 0))
                .location("Local 2")
                .category("Categoria 2")
                .build();
    }
}
