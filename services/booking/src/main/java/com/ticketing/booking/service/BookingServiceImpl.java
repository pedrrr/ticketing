package com.ticketing.booking.service;

import com.ticketing.booking.client.InventoryServiceClientImpl;
import com.ticketing.booking.entity.Customer;
import com.ticketing.booking.exception.BookingValidationException;
import com.ticketing.booking.exception.ResourceNotFoundException;
import com.ticketing.booking.mapper.BookingMapper;
import com.ticketing.booking.request.BookingRequest;
import com.ticketing.booking.response.BookingResponse;
import com.ticketing.common.event.BookingEvent;
import com.ticketing.common.response.EventInventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final CustomerService customerService;
    private final InventoryServiceClientImpl inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> bookingKafkaTemplate;
    public BookingServiceImpl(BookingMapper bookingMapper,
                              CustomerService customerService,
                              InventoryServiceClientImpl inventoryServiceClient,
                              KafkaTemplate<String, BookingEvent> bookingKafkaTemplate) {
        this.bookingMapper = bookingMapper;
        this.customerService = customerService;
        this.inventoryServiceClient = inventoryServiceClient;
        this.bookingKafkaTemplate = bookingKafkaTemplate;
    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        Optional<Customer> customerOptional = customerService.findById(bookingRequest.userId());
        if(customerOptional.isEmpty())
            throw ResourceNotFoundException.customerNotFound(bookingRequest.userId());
        Customer customer = customerOptional.get();

        EventInventoryResponse eventInventoryResponse = inventoryServiceClient.getInventory(bookingRequest.eventId());
        log.info("Inventory response: {}", eventInventoryResponse);

        if(!enoughInventory(eventInventoryResponse.leftCapacity(), bookingRequest.ticketCount()))
            throw BookingValidationException.notEnoughTicketsAtEvent(eventInventoryResponse.id(),
                    eventInventoryResponse.leftCapacity(),
                    bookingRequest.ticketCount());

        BookingEvent bookingEvent = createBookingEvent(bookingRequest, customer, eventInventoryResponse);
        bookingKafkaTemplate.send("booking", bookingEvent);
        log.info("Booking sent to Kafka: {}", bookingEvent);

        return bookingMapper.bookingEventToBookingResponse(bookingEvent);
    }
    private boolean enoughInventory(Long eventLeftCapacity, Long bookingTicketCount){
        return bookingTicketCount <= eventLeftCapacity;
    }
    private BookingEvent createBookingEvent(BookingRequest bookingRequest,
                                            Customer customer,
                                            EventInventoryResponse eventInventoryResponse) {
        return new BookingEvent(
                customer.getId(),
                eventInventoryResponse.id(),
                bookingRequest.ticketCount(),
                eventInventoryResponse.ticketPrice()
                        .multiply(BigDecimal.valueOf(bookingRequest.ticketCount())));
    }

}
