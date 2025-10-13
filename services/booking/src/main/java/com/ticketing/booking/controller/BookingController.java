package com.ticketing.booking.controller;

import com.ticketing.booking.request.BookingRequest;
import com.ticketing.booking.response.BookingResponse;
import com.ticketing.booking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/booking")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody @Valid BookingRequest request){
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

}
