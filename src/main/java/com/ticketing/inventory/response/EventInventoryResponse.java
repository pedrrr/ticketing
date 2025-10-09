package com.ticketing.inventory.response;

import java.math.BigDecimal;

public record EventInventoryResponse(Long id,
                                     String name,
                                     Long leftCapacity,
                                     VenueInventoryResponse venue,
                                     BigDecimal ticketPrice) {
}
