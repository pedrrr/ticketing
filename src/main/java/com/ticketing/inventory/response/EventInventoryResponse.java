package com.ticketing.inventory.response;

public record EventInventoryResponse(String name,
                                     Long leftCapacity,
                                     VenueInventoryResponse venue) {}
