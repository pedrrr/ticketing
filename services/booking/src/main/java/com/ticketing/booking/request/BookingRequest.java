package com.ticketing.booking.request;

public record BookingRequest(
        Long userId,
        Long eventId,
        Long ticketCount) {}
