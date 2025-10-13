package com.ticketing.booking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BookingException extends RuntimeException {
    private final String error;
    private final String errorMessage;
}
