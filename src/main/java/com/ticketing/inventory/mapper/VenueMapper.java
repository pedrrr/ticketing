package com.ticketing.inventory.mapper;

import com.ticketing.inventory.entity.Venue;
import com.ticketing.inventory.response.VenueInventoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    VenueInventoryResponse venueToVenueInventoryResponse(Venue venue);
}
