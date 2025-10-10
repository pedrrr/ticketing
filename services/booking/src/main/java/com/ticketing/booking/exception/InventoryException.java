package com.ticketing.booking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class InventoryException extends RuntimeException {
    private final String error;
    private final String errorMessage;
}
