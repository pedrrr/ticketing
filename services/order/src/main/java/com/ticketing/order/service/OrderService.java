package com.ticketing.order.service;


import com.ticketing.common.event.BookingEvent;

public interface OrderService {
    void orderEvent(BookingEvent bookingEvent);
}
