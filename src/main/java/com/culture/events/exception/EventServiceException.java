package com.culture.events.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EventServiceException extends RuntimeException {
    private final String field;
    private final String message;
    private final String value;
    private final HttpStatus httpStatus;

    public EventServiceException(String field, String message, String value, HttpStatus httpStatus) {
        super(message);

        this.field = field;
        this.message = message;
        this.value = value;
        this.httpStatus = httpStatus;
    }

    public String getMethod() {
        return field;
    }
}
