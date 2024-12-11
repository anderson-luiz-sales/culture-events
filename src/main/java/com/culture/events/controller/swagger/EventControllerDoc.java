package com.culture.events.controller.swagger;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.dtos.request.EventRequestListDTO;
import com.culture.events.dtos.response.EventResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EventControllerDoc {

    @PostMapping
    @Operation(summary = "Cria um novo evento", description = "Este endpoint cria um novo evento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    })
    ResponseEntity<Void> createEvent(@RequestBody EventRequestListDTO eventRequestList);

    @GetMapping
    @Operation(summary = "Obtém todos os eventos", description = "Este endpoint retorna uma lista de todos os eventos.")
    @ApiResponse(responseCode = "200", description = "Lista de eventos retornada com sucesso.")
    ResponseEntity<List<EventResponseDTO>> getAllEvents();

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um evento por ID", description = "Este endpoint retorna um evento específico com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado."),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado.")
    })
    ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um evento por ID", description = "Este endpoint deleta um evento específico com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado.")
    })
    ResponseEntity<Void> deleteEvent(@PathVariable Long id);

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um evento por ID", description = "Este endpoint atualiza um evento específico com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    })
    ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @RequestBody EventRequestDTO eventRequest);
}