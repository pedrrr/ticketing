package com.ticketing.booking.response;

import java.math.BigDecimal;

public record BookingResponse(Long userId,
                              Long eventId,
                              Long ticketCount,
                              BigDecimal totalPrice) {}
