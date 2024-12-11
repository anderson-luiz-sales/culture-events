package com.culture.events.service;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.dtos.request.EventRequestListDTO;
import com.culture.events.dtos.response.EventResponseDTO;
import com.culture.events.entities.Event;
import com.culture.events.exception.EventServiceException;
import com.culture.events.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.culture.events.factory.EventFactory.makeEventFirst;
import static com.culture.events.factory.EventRequestDTOFactory.makeEventRequestDTOFirst;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventServiceTest {

    private static final Long EVENT_ID = 1L;
    private static final String EVENT_NOT_FOUND_MESSAGE = "Evento não encontrado.";
    private static final String EVENT_SERVICE_CREATE_ERROR = "Erro inesperado ao criar eventos.";
    private static final String EVENT_SERVICE_LIST_ERROR = "Erro inesperado ao consultar eventos.";

    private EventRepository eventRepository;
    private EventService eventService;

    @BeforeEach
    void setUp() {
        eventRepository = mock(EventRepository.class);
        eventService = new EventService(eventRepository);
    }

    @Test
    void testCreateEvent() {
        EventRequestListDTO eventRequestList = new EventRequestListDTO();
        eventRequestList.setEvents(Collections.singletonList(makeEventRequestDTOFirst()));

        eventService.createEvent(eventRequestList);

        verify(eventRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testCreateEventShouldThrowEventServiceExceptionWhenRepositoryFails() {
        EventRequestListDTO eventRequestList = new EventRequestListDTO();
        eventRequestList.setEvents(Collections.singletonList(makeEventRequestDTOFirst()));

        doThrow(new RuntimeException("Database error")).when(eventRepository).saveAll(anyList());

        EventServiceException exception = assertThrows(EventServiceException.class, () -> eventService.createEvent(eventRequestList));

        assertEquals(EVENT_SERVICE_CREATE_ERROR, exception.getMessage());
    }

    @Test
    void testGetAllEvents() {
        Event event = makeEventFirst();
        when(eventRepository.findAll()).thenReturn(Collections.singletonList(event));

        List<EventResponseDTO> events = eventService.getAllEvents();

        assertEquals(1, events.size());
        assertEquals("Concerto de Música", events.get(0).getEventName());
    }

    @Test
    void testGetAllEventsShouldThrowEventServiceExceptionWhenRepositoryFails() {
        when(eventRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        EventServiceException exception = assertThrows(EventServiceException.class, () -> eventService.getAllEvents());

        assertEquals(EVENT_SERVICE_LIST_ERROR, exception.getMessage());
    }

    @Test
    void testGetEventByIdExists() {
        Event event = makeEventFirst();
        when(eventRepository.findById(EVENT_ID)).thenReturn(Optional.of(event));

        EventResponseDTO eventResponse = eventService.getEventById(EVENT_ID);

        assertEquals("Concerto de Música", eventResponse.getEventName());
    }


    @Test
    void testGetEventById_NotFound() {
        when(eventRepository.findById(EVENT_ID)).thenReturn(Optional.empty());

        EventServiceException exception = assertThrows(EventServiceException.class, () -> {
            eventService.getEventById(EVENT_ID);
        });
        assertEquals(EVENT_NOT_FOUND_MESSAGE, exception.getMessage());
    }

    @Test
    void testDeleteEventExists() {
        Event event = makeEventFirst();
        when(eventRepository.findById(EVENT_ID)).thenReturn(Optional.of(event));

        eventService.deleteEvent(EVENT_ID);

        verify(eventRepository, times(1)).delete(event);
    }

    @Test
    void testDeleteEventNotFound() {
        when(eventRepository.findById(EVENT_ID)).thenReturn(Optional.empty());

        EventServiceException exception = assertThrows(EventServiceException.class, () -> eventService.deleteEvent(EVENT_ID));
        assertEquals(EVENT_NOT_FOUND_MESSAGE, exception.getMessage());
    }

    @Test
    void testUpdateEventExists() {
        EventRequestDTO eventRequest = makeEventRequestDTOFirst();
        Event event = makeEventFirst();
        when(eventRepository.findById(EVENT_ID)).thenReturn(Optional.of(event));

        EventResponseDTO updatedEvent = eventService.updateEvent(EVENT_ID, eventRequest);

        assertEquals("Concerto de Música", updatedEvent.getEventName());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void testUpdateEvent_NotFound() {
        EventRequestDTO eventRequest = makeEventRequestDTOFirst();
        when(eventRepository.findById(EVENT_ID)).thenReturn(Optional.empty());

        EventServiceException exception = assertThrows(EventServiceException.class, () -> eventService.updateEvent(EVENT_ID, eventRequest));
        assertEquals(EVENT_NOT_FOUND_MESSAGE, exception.getMessage());
    }
}
