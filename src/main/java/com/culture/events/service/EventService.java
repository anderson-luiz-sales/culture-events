package com.culture.events.service;

import com.culture.events.dtos.request.EventRequestListDTO;
import com.culture.events.dtos.response.EventResponseDTO;
import com.culture.events.entities.Event;
import com.culture.events.exception.EventServiceException;
import com.culture.events.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.culture.events.service.mapper.EventMapper.mapToEventList;
import static com.culture.events.service.mapper.EventResponseDTOMapper.mapToEventResponseList;
import static com.culture.events.utils.ErrorLogsUtils.EVENT_SERVICE_CREATE_ERROR;
import static com.culture.events.utils.ErrorLogsUtils.EVENT_SERVICE_LIST_ERROR;

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
            throw new EventServiceException(methodName, EVENT_SERVICE_CREATE_ERROR, e.getMessage());
        }
    }

    public List<EventResponseDTO> getAllEvents() {
        String methodName = "getAllEvents";
        try {
            List<Event> events = eventRepository.findAll();
            return mapToEventResponseList(events);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, EVENT_SERVICE_LIST_ERROR, e.getMessage(), e);
            throw new EventServiceException(methodName, EVENT_SERVICE_LIST_ERROR,  e.getMessage());
        }
    }
}
