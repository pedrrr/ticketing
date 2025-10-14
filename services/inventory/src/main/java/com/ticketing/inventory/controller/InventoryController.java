package com.ticketing.inventory.controller;

import com.ticketing.common.response.EventInventoryResponse;
import com.ticketing.common.response.VenueInventoryResponse;
import com.ticketing.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

	private final InventoryService inventoryService;
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/events")
	public ResponseEntity<List<EventInventoryResponse>> inventoryGetAllEvents() {
		return ResponseEntity.ok(inventoryService.getAllEvents());
	}

	@GetMapping("/events/{eventId}")
	public ResponseEntity<EventInventoryResponse> inventoryForEvent(@PathVariable Long eventId) {
		return inventoryService.getEventInventory(eventId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/venue/{venueId}")
	public ResponseEntity<VenueInventoryResponse> inventoryVenueById(@PathVariable Long venueId) {
		return inventoryService.getVenueInformation(venueId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

    @PutMapping("/events/{eventId}/capacity/{ticketsBooked}")
    public ResponseEntity<EventInventoryResponse> updateEventCapacity(@PathVariable Long eventId,
                                                                      @PathVariable Long ticketsBooked) {
        return inventoryService.updateEventCapacity(eventId, ticketsBooked).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}


