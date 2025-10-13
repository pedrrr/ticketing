package com.ticketing.inventory.service;

import com.ticketing.common.response.EventInventoryResponse;
import com.ticketing.common.response.VenueInventoryResponse;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
	List<EventInventoryResponse> getAllEvents();
	Optional<VenueInventoryResponse> getVenueInformation(Long venueId);
	Optional<EventInventoryResponse> getEventInventory(Long eventId);
    Optional<EventInventoryResponse> updateEventCapacity(Long eventId, Long ticketsBooked);
}


