package com.culture.events.controller;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.dtos.response.EventResponseDTO;
import com.culture.events.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventControllerTest {

    private static final Long EVENT_ID = 1L;

    @InjectMocks
    private EventController eventController;

    @Mock
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {
        EventRequestDTO eventRequestDTO = new EventRequestDTO();

        ResponseEntity<Void> response = eventController.createEvent(eventRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(eventService, times(1)).createEvent(eventRequestDTO);
    }

    @Test
    void testGetAllEvents() {
        EventResponseDTO eventResponse = new EventResponseDTO();
        when(eventService.getAllEvents()).thenReturn(Collections.singletonList(eventResponse));

        ResponseEntity<List<EventResponseDTO>> response = eventController.getAllEvents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(eventService, times(1)).getAllEvents();
    }

    @Test
    void testGetEventById() {
        EventResponseDTO eventResponse = new EventResponseDTO();
        when(eventService.getEventById(EVENT_ID)).thenReturn(eventResponse);

        ResponseEntity<EventResponseDTO> response = eventController.getEventById(EVENT_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(eventResponse, response.getBody());
        verify(eventService, times(1)).getEventById(EVENT_ID);
    }

    @Test
    void testDeleteEvent() {
      ResponseEntity<Void> response = eventController.deleteEvent(EVENT_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventService, times(1)).deleteEvent(EVENT_ID);
    }

    @Test
    void testUpdateEvent() {
        EventRequestDTO eventRequest = new EventRequestDTO();
        EventResponseDTO updatedEventResponse = new EventResponseDTO();
        when(eventService.updateEvent(EVENT_ID, eventRequest)).thenReturn(updatedEventResponse);

        ResponseEntity<EventResponseDTO> response = eventController.updateEvent(EVENT_ID, eventRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedEventResponse, response.getBody());
        verify(eventService, times(1)).updateEvent(EVENT_ID, eventRequest);
    }
}
