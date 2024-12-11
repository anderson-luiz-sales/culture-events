package com.culture.events.controller;

import com.culture.events.controller.swagger.EventControllerDoc;
import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.dtos.response.EventResponseDTO;
import com.culture.events.exception.BadRequestMessageException;
import com.culture.events.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/events")
@Slf4j
@BadRequestMessageException("Bad request")
@RequiredArgsConstructor
public class EventController implements EventControllerDoc {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Void> createEvent(@Valid @RequestBody EventRequestDTO eventRequestList) {
        log.info("Creating events: {}", eventRequestList);
        eventService.createEvent(eventRequestList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        log.info("Fetching all events");
        List<EventResponseDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        log.info("Fetching event with ID: {}", id);
        EventResponseDTO eventResponse = eventService.getEventById(id);
        return ResponseEntity.ok(eventResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        log.info("Deleting event with ID: {}", id);
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @Valid @RequestBody EventRequestDTO eventRequest) {
        log.info("Updating event with ID: {}", id);
        EventResponseDTO updatedEvent = eventService.updateEvent(id, eventRequest);
        return ResponseEntity.ok(updatedEvent);
    }
}

