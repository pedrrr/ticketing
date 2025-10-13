package com.ticketing.booking.client;

import com.ticketing.booking.response.InventoryResponse;

import java.util.Optional;

public interface InventoryServiceClient {
    InventoryResponse getInventory(Long eventId);
}
