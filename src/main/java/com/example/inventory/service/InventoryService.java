package com.example.inventory.service;

import com.example.inventory.response.EventInventoryResponse;
import com.example.inventory.response.VenueInventoryResponse;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    List<EventInventoryResponse> getAllEvents();
    Optional<VenueInventoryResponse> getVenueInformation(Long venueId);
}
