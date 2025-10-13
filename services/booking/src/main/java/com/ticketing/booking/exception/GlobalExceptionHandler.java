package com.ticketing.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleRequestValidationExceptions(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
        String invalidField = fieldError.getField();
        String errorMessage = fieldError.getDefaultMessage();

        BookingValidationException bookingValidationException =
                BookingValidationException.invalidValueProvided(invalidField, errorMessage);

        Map<String, Object> response = mapErrors(bookingValidationException, HttpStatus.BAD_REQUEST);
        response.put("invalidField", bookingValidationException.getValidationField());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BookingValidationException.class)
    public ResponseEntity<Map<String, Object>> handleBookingValidationException(BookingValidationException ex) {
        Map<String, Object> response = mapErrors(ex, HttpStatus.BAD_REQUEST);

        response.put("validationField", ex.getValidationField());
        if(ex.getEventId() != null) {
            response.put("eventId", ex.getEventId());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> response = mapErrors(ex, HttpStatus.NOT_FOUND);

        if (ex.getResourceType() != null) {
            response.put("resourceType", ex.getResourceType());
        }
        if (ex.getResourceId() != null) {
            response.put("resourceId", ex.getResourceId());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    private Map<String, Object> mapErrors(BookingException ex, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("timestamp", LocalDateTime.now());
        response.put("error", ex.getError());
        response.put("errorMessage", ex.getErrorMessage());
        return response;
    }
}
