package com.ticketing.inventory.mapper;

import com.ticketing.common.response.VenueInventoryResponse;
import com.ticketing.inventory.entity.Venue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {
	VenueInventoryResponse venueToVenueInventoryResponse(Venue venue);
}


