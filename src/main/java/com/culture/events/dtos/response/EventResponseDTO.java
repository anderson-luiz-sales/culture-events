package com.culture.events.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Builder)
public class EventResponseDTO {

    private Long id;
    private String eventName;
    private String description;
    private LocalDateTime eventDate;
    private String location;
    private String category;
}
