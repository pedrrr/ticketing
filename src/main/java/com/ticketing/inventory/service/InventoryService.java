package com.ticketing.inventory.service;

import com.ticketing.inventory.response.EventInventoryResponse;
import com.ticketing.inventory.response.VenueInventoryResponse;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    List<EventInventoryResponse> getAllEvents();
    Optional<VenueInventoryResponse> getVenueInformation(Long venueId);
    Optional<EventInventoryResponse> getEventInventory(Long eventId);
}
