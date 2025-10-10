package com.ticketing.inventory.mapper;

import com.ticketing.inventory.entity.Venue;
import com.ticketing.inventory.response.VenueInventoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {
	VenueInventoryResponse venueToVenueInventoryResponse(Venue venue);
}


