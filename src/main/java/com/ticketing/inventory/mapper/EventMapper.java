package com.ticketing.inventory.mapper;

import com.ticketing.inventory.entity.Event;
import com.ticketing.inventory.response.EventInventoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {VenueMapper.class})
public interface EventMapper {
    EventInventoryResponse eventToEventInventoryResponse(Event Event);
}
