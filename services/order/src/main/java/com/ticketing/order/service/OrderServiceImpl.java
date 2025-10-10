package com.ticketing.order.service;

import com.ticketing.order.entity.Order;
import com.ticketing.order.event.BookingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Recieved booking event: {}", bookingEvent);

        Order order = createOrder(bookingEvent);
    }
    private Order createOrder(BookingEvent bookingEvent){
        return Order.builder()
                .customerId(bookingEvent.userId())
                .eventId(bookingEvent.eventId())
                .ticketCount(bookingEvent.ticketCount())
                .totalPrice(bookingEvent.totalPrice())
                .build();
    }
}
