package com.ticketing.order.service;

import com.ticketing.order.event.BookingEvent;

public interface OrderService {
    void orderEvent(BookingEvent bookingEvent);
}
