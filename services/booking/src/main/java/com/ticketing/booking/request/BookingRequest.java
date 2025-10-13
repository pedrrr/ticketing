package com.ticketing.booking.request;

import jakarta.validation.constraints.Min;

public record BookingRequest(
        Long userId,
        Long eventId,
        @Min(value = 1, message = "Invalid ticket quantity.")
        Long ticketCount) {}
