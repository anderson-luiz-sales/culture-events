package com.culture.events.service;

import com.culture.events.dtos.request.EventRequestDTO;
import com.culture.events.dtos.request.EventRequestListDTO;
import com.culture.events.entities.Event;
import com.culture.events.exception.EventServiceException;
import com.culture.events.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.culture.events.service.mapper.EventMapper.mapToEventList;
import static com.culture.events.utils.ErrorLogsUtils.EVENT_SERVICE_CREATE_ERROR;

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
}
