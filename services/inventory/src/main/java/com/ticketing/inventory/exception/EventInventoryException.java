package com.ticketing.inventory.exception;

import lombok.Getter;

@Getter
public class EventInventoryException extends InventoryException {

    private final Long eventId;

    public EventInventoryException(String errorCode, String errorMessage, Long eventId) {
        super(errorCode, errorMessage);
        this.eventId = eventId;
    }

    public EventInventoryException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.eventId = null;
    }

    public static EventInventoryException notEnoughTickets(Long eventId) {
        return new EventInventoryException("NOT_ENOUGH_TICKETS", "Event out of enough tickets to proceed with purchase.", eventId);
    }
}
