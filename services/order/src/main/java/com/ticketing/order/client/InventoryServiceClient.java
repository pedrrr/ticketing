package com.ticketing.order.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

public interface InventoryServiceClient {
    public ResponseEntity<Void> updateInventory(Long eventId, Long ticketsBooked);
}
