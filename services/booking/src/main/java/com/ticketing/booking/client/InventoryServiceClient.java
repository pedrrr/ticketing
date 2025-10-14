package com.ticketing.booking.client;
import com.ticketing.common.response.EventInventoryResponse;

public interface InventoryServiceClient {
    EventInventoryResponse getInventory(Long eventId);
}
