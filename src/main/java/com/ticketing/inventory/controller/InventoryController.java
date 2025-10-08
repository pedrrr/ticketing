package com.ticketing.inventory.controller;

import com.ticketing.inventory.response.EventInventoryResponse;
import com.ticketing.inventory.response.VenueInventoryResponse;
import com.ticketing.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private final InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory/events")
    public ResponseEntity<List<EventInventoryResponse>> inventoryGetAllEvents() {
        return ResponseEntity.ok(inventoryService.getAllEvents());
    }

    @GetMapping("/inventory/events/{eventId}")
    public ResponseEntity<EventInventoryResponse> inventoryForEvent(@PathVariable Long eventId) {
        return inventoryService.getEventInventory(eventId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/inventory/venue/{venueId}")
    public ResponseEntity<VenueInventoryResponse> inventoryVenueById(@PathVariable Long venueId) {
        return inventoryService.getVenueInformation(venueId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
