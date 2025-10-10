package com.ticketing.booking.response;

import java.math.BigDecimal;

public record InventoryResponse(Long id,
                                String name,
                                Long leftCapacity,
                                VenueResponse venue,
                                BigDecimal ticketPrice) { }
