package com.culture.events.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @GetMapping("/events")
    @Operation(summary = "Lista todos os eventos", description = "Este endpoint retorna a lista de eventos disponíveis.")
    public String getEvents() {
        return "List of events";
    }
}

