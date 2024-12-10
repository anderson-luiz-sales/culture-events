package com.culture.events.controller;

import com.culture.events.dtos.request.EventRequestListDTO;
import com.culture.events.dtos.response.EventResponseDTO;
import com.culture.events.exception.BadRequestMessageException;
import com.culture.events.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/events")
@Slf4j
@BadRequestMessageException("Bad request")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    @Operation(summary = "Cria um novo evento", description = "Este endpoint cria um novo evento.")
    public ResponseEntity<Void> createEvent(@Valid @RequestBody EventRequestListDTO eventRequestList) {
        log.info("Creating events: {}", eventRequestList);
        eventService.createEvent(eventRequestList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Obtém todos os eventos", description = "Este endpoint retorna uma lista de todos os eventos.")
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        log.info("Fetching all events");
        List<EventResponseDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}

