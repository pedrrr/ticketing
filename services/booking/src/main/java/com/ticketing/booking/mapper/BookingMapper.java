package com.ticketing.booking.mapper;

import com.ticketing.booking.entity.Booking;
import com.ticketing.booking.event.BookingEvent;
import com.ticketing.booking.request.BookingRequest;
import com.ticketing.booking.response.BookingResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingResponse bookingToBookingResponse(Booking booking);
    Booking bookingRequestToBooking(BookingRequest bookingRequest);
    BookingResponse bookingEventToBookingResponse(BookingEvent bookingEvent);
}
