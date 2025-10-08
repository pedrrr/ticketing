package com.example.inventory.controller;

import com.example.inventory.response.EventInventoryResponse;
import com.example.inventory.response.VenueInventoryResponse;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/inventory/venue/{venueId}")
    public ResponseEntity<VenueInventoryResponse> inventoryVenueById(@PathVariable Long venueId) {
        return inventoryService.getVenueInformation(venueId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
