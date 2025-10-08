package com.ticketing.inventory.service;

import com.ticketing.inventory.entity.Event;
import com.ticketing.inventory.mapper.EventMapper;
import com.ticketing.inventory.mapper.VenueMapper;
import com.ticketing.inventory.repository.EventRepository;
import com.ticketing.inventory.repository.VenueRepository;
import com.ticketing.inventory.response.EventInventoryResponse;
import com.ticketing.inventory.response.VenueInventoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final EventMapper eventMapper;
    private final VenueMapper venueMapper;
    public InventoryServiceImpl(EventRepository eventRepository,
                                VenueRepository venueRepository,
                                EventMapper eventMapper,
                                VenueMapper venueMapper) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.eventMapper = eventMapper;
        this.venueMapper = venueMapper;
    }

    @Override
    public List<EventInventoryResponse> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventMapper::eventToEventInventoryResponse).toList();
    }

    @Override
    public Optional<VenueInventoryResponse> getVenueInformation(Long venueId) {
        return venueRepository.findById(venueId).map(venueMapper::venueToVenueInventoryResponse);
    }

}
