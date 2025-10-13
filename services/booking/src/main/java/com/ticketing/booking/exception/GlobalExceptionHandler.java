package com.ticketing.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookingValidationException.class)
    public ResponseEntity<Map<String, Object>> handleBookingValidationException(BookingValidationException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("error", ex.getError());
        response.put("errorMessage", ex.getErrorMessage());
        response.put("validationField", ex.getValidationField());
        if(ex.getEventId() != null) {
            response.put("eventId", ex.getEventId());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("error", ex.getError());
        response.put("errorMessage", ex.getErrorMessage());
        if (ex.getResourceType() != null) {
            response.put("resourceType", ex.getResourceType());
        }
        if (ex.getResourceId() != null) {
            response.put("resourceId", ex.getResourceId());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
