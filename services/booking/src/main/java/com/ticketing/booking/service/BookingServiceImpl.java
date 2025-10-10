package com.ticketing.booking.service;

import com.ticketing.booking.client.InventoryServiceClientImpl;
import com.ticketing.booking.entity.Customer;
import com.ticketing.booking.mapper.BookingMapper;
import com.ticketing.booking.repository.BookingRepository;
import com.ticketing.booking.request.BookingRequest;
import com.ticketing.booking.response.BookingResponse;
import com.ticketing.booking.response.InventoryResponse;
import com.ticketing.common.event.BookingEvent;
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
            throw new RuntimeException("User not found."); // todo: handle exeption with advices
        Customer customer = customerOptional.get();

        Optional<InventoryResponse> inventoryResponseOptional = inventoryServiceClient.getInventory(bookingRequest.eventId());
        if(inventoryResponseOptional.isEmpty())
            throw new RuntimeException("Event not found."); // todo: handle exeption with advices
        InventoryResponse inventoryResponse = inventoryResponseOptional.get();
        log.info("Inventory response: {}", inventoryResponse);

        if(!enoughInventory(inventoryResponse.leftCapacity(), bookingRequest.ticketCount()))
            throw new RuntimeException("Not enough inventory."); // todo: handle exeption with advices

        BookingEvent bookingEvent = createBookingEvent(bookingRequest, customer, inventoryResponse);
        bookingKafkaTemplate.send("booking", bookingEvent);
        log.info("Booking sent to Kafka: {}", bookingEvent);

        return bookingMapper.bookingEventToBookingResponse(bookingEvent);
    }
    private boolean enoughInventory(Long eventLeftCapacity, Long bookingTicketCount){
        return bookingTicketCount <= eventLeftCapacity;
    }
    private BookingEvent createBookingEvent(BookingRequest bookingRequest,
                                            Customer customer,
                                            InventoryResponse inventoryResponse) {
        return new BookingEvent(
                customer.getId(),
                inventoryResponse.id(),
                bookingRequest.ticketCount(),
                inventoryResponse.ticketPrice()
                        .multiply(BigDecimal.valueOf(bookingRequest.ticketCount())));
    }

}
