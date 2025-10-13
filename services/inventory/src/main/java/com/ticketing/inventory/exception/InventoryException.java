package com.ticketing.inventory.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public abstract class InventoryException extends RuntimeException {
    private final String error;
    private final String errorMessage;
}
