package com.culture.events.mapper;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.entities.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.culture.events.factory.EventRequestDTOFactory.makeEventRequestDTO;
import static com.culture.events.service.mapper.EventMapper.mapToEvent;
import static com.culture.events.service.mapper.EventMapper.updateEventFromRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventMapperTest {

    @Test
    void testMapToEvent() {
        EventRequestDTO eventRequest = makeEventRequestDTO();
        Event event = mapToEvent(eventRequest);

        assertEquals("Concerto de Música", event.getEventName());
        assertEquals("Um concerto de música clássica ao ar livre.", event.getDescription());
        assertTrue(event.getEventDate().isEqual(LocalDateTime.of(2024, 5, 10, 20, 0)));
        assertEquals("Central Park", event.getLocation());
        assertEquals("Música", event.getCategory());
    }

    @Test
    void testUpdateEventFromRequest() {
        Event event = new Event();
        EventRequestDTO eventRequest = makeEventRequestDTO();

        updateEventFromRequest(event, eventRequest);

        assertEquals("Concerto de Música", event.getEventName());
        assertEquals("Um concerto de música clássica ao ar livre.", event.getDescription());
        assertTrue(event.getEventDate().isEqual(LocalDateTime.of(2024, 5, 10, 20, 0)));
        assertEquals("Central Park", event.getLocation());
        assertEquals("Música", event.getCategory());
    }
}
