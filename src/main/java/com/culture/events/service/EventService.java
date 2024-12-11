package com.culture.events.service;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.dtos.request.EventRequestListDTO;
import com.culture.events.dtos.response.EventResponseDTO;
import com.culture.events.entities.Event;
import com.culture.events.exception.EventServiceException;
import com.culture.events.repositories.EventRepository;
import com.culture.events.service.mapper.EventResponseDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.culture.events.service.mapper.EventMapper.mapToEventList;
import static com.culture.events.service.mapper.EventMapper.updateEventFromRequest;
import static com.culture.events.service.mapper.EventResponseDTOMapper.mapToEventResponse;
import static com.culture.events.service.mapper.EventResponseDTOMapper.mapToEventResponseList;
import static com.culture.events.utils.ErrorLogsUtils.EVENT_SERVICE_CREATE_ERROR;
import static com.culture.events.utils.ErrorLogsUtils.EVENT_SERVICE_LIST_BY_ID_ERROR;
import static com.culture.events.utils.ErrorLogsUtils.EVENT_SERVICE_LIST_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private static final String ERROR_MESSAGE = "{}: {}";

    private final EventRepository eventRepository;

    public void createEvent(EventRequestListDTO eventRequestList) {
        String methodName = "createEvent";
        try {
            List<Event> eventList = mapToEventList(eventRequestList);
            eventRepository.saveAll(eventList);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, EVENT_SERVICE_CREATE_ERROR, e.getMessage(), e);
            throw new EventServiceException(methodName, EVENT_SERVICE_CREATE_ERROR, e.getMessage(), UNPROCESSABLE_ENTITY);
        }
    }

    public List<EventResponseDTO> getAllEvents() {
        String methodName = "getAllEvents";
        try {
            List<Event> events = eventRepository.findAll();
            return mapToEventResponseList(events);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, EVENT_SERVICE_LIST_ERROR, e.getMessage(), e);
            throw new EventServiceException(methodName, EVENT_SERVICE_LIST_ERROR, e.getMessage(), UNPROCESSABLE_ENTITY);
        }
    }

    public EventResponseDTO getEventById(Long id) {
        String methodName = "getEventById";
        Optional<Event> eventOptional = eventRepository.findById(id);
        return eventOptional
                .map(EventResponseDTOMapper::mapToEventResponse)
                .orElseThrow(() -> new EventServiceException(methodName, EVENT_SERVICE_LIST_BY_ID_ERROR, "ID: " + id, NOT_FOUND));
    }

    public void deleteEvent(Long id) {
        String methodName = "deleteEvent";
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            eventRepository.delete(eventOptional.get());
        } else {
            throw new EventServiceException(methodName, EVENT_SERVICE_LIST_BY_ID_ERROR, "ID: " + id, NOT_FOUND);
        }
    }

    public EventResponseDTO updateEvent(Long id, EventRequestDTO eventRequest) {
        String methodName = "updateEvent";
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            updateEventFromRequest(event, eventRequest);
            eventRepository.save(event);
            return mapToEventResponse(event);
        } else {
            throw new EventServiceException(methodName, EVENT_SERVICE_LIST_BY_ID_ERROR, "ID: " + id, NOT_FOUND);
        }
    }
}
