package com.culture.events.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Builder)
public class EventRequestDTO {

    @Schema(type = "string", description = "Nome do Evento", example = "Concerto de Música")
    @NotEmpty(message = "O campo 'eventName' é obrigatório.")
    @Size(max = 100, message = "O campo 'eventName' deve ter no máximo 100 caracteres.")
    private String eventName;

    @Schema(type = "string", description = "Descrição do Evento", example = "Um concerto de música clássica ao ar livre.")
    @Size(max = 500, message = "O campo 'description' deve ter no máximo 500 caracteres.")
    private String description;

    @Schema(type = "string", description = "Data do Evento", example = "2024-05-10T20:00:00")
    @NotNull(message = "O campo 'eventDate' é obrigatório.")
    private LocalDateTime eventDate;

    @Schema(type = "string", description = "Local do Evento", example = "Central Park")
    @NotEmpty(message = "O campo 'location' é obrigatório.")
    private String location;

    @Schema(type = "string", description = "Categoria do Evento", example = "Música")
    @NotEmpty(message = "O campo 'category' é obrigatório.")
    private String category;
}