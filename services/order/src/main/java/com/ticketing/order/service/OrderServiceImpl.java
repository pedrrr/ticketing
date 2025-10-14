package com.ticketing.order.service;

import com.ticketing.common.event.BookingEvent;
import com.ticketing.order.client.InventoryServiceClient;
import com.ticketing.order.entity.Order;
import com.ticketing.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryService;
    public OrderServiceImpl(OrderRepository orderRepository,
                            InventoryServiceClient inventoryService) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
    }

    @Override
    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Received booking event: {}", bookingEvent);

        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);

        inventoryService.updateInventory(order.getEventId(),  order.getTicketCount());
        log.info("Inventory updated for event: {}, removed tickets: {}.", order.getEventId(), order.getTicketCount());
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
