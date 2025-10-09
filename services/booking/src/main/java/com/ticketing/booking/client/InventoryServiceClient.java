package com.ticketing.booking.client;

import com.ticketing.booking.response.InventoryResponse;

import java.util.Optional;

public interface InventoryServiceClient {
    Optional<InventoryResponse> getInventory(Long eventId);
}
