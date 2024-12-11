package com.culture.events.mapper;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.dtos.request.EventRequestListDTO;
import com.culture.events.entities.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.culture.events.factory.EventRequestDTOFactory.makeEventRequestDTOFirst;
import static com.culture.events.factory.EventRequestDTOFactory.makeEventRequestDTOSecond;
import static com.culture.events.service.mapper.EventMapper.mapToEvent;
import static com.culture.events.service.mapper.EventMapper.mapToEventList;
import static com.culture.events.service.mapper.EventMapper.updateEventFromRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventMapperTest {

    @Test
    void testMapToEvent() {
        EventRequestDTO eventRequest = makeEventRequestDTOFirst();
        Event event = mapToEvent(eventRequest);

        assertEquals("Concerto de Música", event.getEventName());
        assertEquals("Um concerto de música clássica ao ar livre.", event.getDescription());
        assertTrue(event.getEventDate().isEqual(LocalDateTime.of(2024, 5, 10, 20, 0)));
        assertEquals("Central Park", event.getLocation());
        assertEquals("Música", event.getCategory());
    }

    @Test
    void testMapToEventList() {
        EventRequestDTO eventRequestFirst = makeEventRequestDTOFirst();
        EventRequestDTO eventRequestSecond = makeEventRequestDTOSecond();

        EventRequestListDTO eventRequestList = new EventRequestListDTO();
        eventRequestList.setEvents(List.of(eventRequestFirst, eventRequestSecond));

        List<Event> events = mapToEventList(eventRequestList);

        assertEquals(2, events.size());
        assertEquals("Concerto de Música", events.get(0).getEventName());
        assertEquals("Show de Dança", events.get(1).getEventName());
    }

    @Test
    void testUpdateEventFromRequest() {
        Event event = new Event();
        EventRequestDTO eventRequest = makeEventRequestDTOFirst();

        updateEventFromRequest(event, eventRequest);

        assertEquals("Concerto de Música", event.getEventName());
        assertEquals("Um concerto de música clássica ao ar livre.", event.getDescription());
        assertTrue(event.getEventDate().isEqual(LocalDateTime.of(2024, 5, 10, 20, 0)));
        assertEquals("Central Park", event.getLocation());
        assertEquals("Música", event.getCategory());
    }
}
