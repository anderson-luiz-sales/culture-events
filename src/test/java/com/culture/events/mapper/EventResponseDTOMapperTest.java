package com.culture.events.mapper;

import com.culture.events.dtos.response.EventResponseDTO;
import com.culture.events.entities.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.culture.events.factory.EventFactory.makeEventFirst;
import static com.culture.events.factory.EventFactory.makeEventSecond;
import static com.culture.events.factory.EventResponseDTOFactory.makeEventResponseDTO;
import static com.culture.events.service.mapper.EventResponseDTOMapper.mapToEventResponseList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventResponseDTOMapperTest {

    @Test
    void testMapToEventResponse() {
        EventResponseDTO eventResponse = makeEventResponseDTO();

        assertEquals(1L, eventResponse.getId());
        assertEquals("Concerto de Música", eventResponse.getEventName());
        assertEquals("Um concerto de música clássica ao ar livre.", eventResponse.getDescription());
        assertEquals(LocalDateTime.of(2024, 5, 10, 20, 0), eventResponse.getEventDate());
        assertEquals("Central Park", eventResponse.getLocation());
        assertEquals("Música", eventResponse.getCategory());
    }

    @Test
    void testMapToEventResponseList() {
        Event event1 = makeEventFirst();
        Event event2 = makeEventSecond();

        List<Event> events = List.of(event1, event2);
        List<EventResponseDTO> eventResponses = mapToEventResponseList(events);

        assertEquals(2, eventResponses.size());

        assertEquals(1L, eventResponses.get(0).getId());
        assertEquals("Concerto de Música", eventResponses.get(0).getEventName());
        assertEquals("Um concerto de música clássica ao ar livre.", eventResponses.get(0).getDescription());
        assertEquals(LocalDateTime.of(2024, 5, 10, 20, 0), eventResponses.get(0).getEventDate());
        assertEquals("Central Park", eventResponses.get(0).getLocation());
        assertEquals("Música", eventResponses.get(0).getCategory());

        assertEquals(2L, eventResponses.get(1).getId());
        assertEquals("Show de dança", eventResponses.get(1).getEventName());
        assertEquals("Descrição 2", eventResponses.get(1).getDescription());
        assertEquals(LocalDateTime.of(2024, 6, 10, 20, 0), eventResponses.get(1).getEventDate());
        assertEquals("Local 2", eventResponses.get(1).getLocation());
        assertEquals("Categoria 2", eventResponses.get(1).getCategory());
    }
}
