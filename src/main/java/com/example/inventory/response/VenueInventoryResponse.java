package com.example.inventory.response;

import com.example.inventory.entity.Venue;

public record VenueInventoryResponse(Long venueId, String venueName, Long totalCapacity) {
    public static VenueInventoryResponse fromEntity(Venue venue) {
        return new VenueInventoryResponse(
                venue.getId(),
                venue.getName(),
                venue.getTotalCapacity());
    }
}
