package com.culture.events.controller;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.dtos.request.EventRequestListDTO;
import com.culture.events.exception.BadRequestMessageException;
import com.culture.events.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    @Operation(summary = "Cria um novo evento", description = "Este endpoint cria um novo evento.")
    public ResponseEntity<Void> createEvent(@Valid @RequestBody EventRequestListDTO eventRequestList) {
        log.info("Creating events: {}", eventRequestList);
        eventService.createEvent(eventRequestList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

