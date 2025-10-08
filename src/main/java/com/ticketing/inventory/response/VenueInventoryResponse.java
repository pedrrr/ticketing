package com.ticketing.inventory.response;

import com.ticketing.inventory.entity.Venue;

public record VenueInventoryResponse(Long id,
                                     String name,
                                     Long totalCapacity) {}
