package com.ticketing.booking.exception;

import lombok.Getter;

@Getter
public class BookingValidationException extends BookingException {

    private final Long eventId;
    private final String validationField;

    public BookingValidationException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.eventId = null;
        this.validationField = null;
    }

    public BookingValidationException(String errorCode, String errorMessage, Long eventId) {
        super(errorCode, errorMessage);
        this.eventId = eventId;
        this.validationField = null;
    }

    public BookingValidationException(String errorCode, String errorMessage, String validationField) {
        super(errorCode, errorMessage);
        this.eventId = null;
        this.validationField = validationField;
    }

    public BookingValidationException(String errorCode, String errorMessage, Long eventId, String validationField) {
        super(errorCode, errorMessage);
        this.eventId = eventId;
        this.validationField = validationField;
    }

    public static BookingValidationException notEnoughTicketsAtEvent(Long eventId, Long leftCapacity, Long ticketCount) {
        return new BookingValidationException("NOT_ENOUGH_TICKETS",
                ticketCount + " selected tickets out of " + leftCapacity + " available at event.",
                eventId,
                "ticketCount");
    }

    public static BookingValidationException invalidValueProvided(String validationField, String errorMessage) {
        return new BookingValidationException("INVALID_VALUE_PROVIDED",
                errorMessage,
                validationField);
    }
}
