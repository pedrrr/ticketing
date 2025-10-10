package com.ticketing.booking.service;

import com.ticketing.booking.request.BookingRequest;
import com.ticketing.booking.response.BookingResponse;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);
}
