package com.ticketing.common.event;

import java.math.BigDecimal;

public record BookingEvent(Long userId,
                           Long eventId,
                           Long ticketCount,
                           BigDecimal totalPrice) {}
