package com.example.inventory.response;

import com.example.inventory.entity.Event;
import com.example.inventory.entity.Venue;

public record EventInventoryResponse(String event, Long capacity, Venue venue) {
    public static EventInventoryResponse fromEntity(Event event) {
        return new EventInventoryResponse(
                event.getName(),
                event.getLeftCapacity(),
                event.getVenue());
    }
}
