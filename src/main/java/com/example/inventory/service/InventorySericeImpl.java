package com.example.inventory.service;

import com.example.inventory.entity.Event;
import com.example.inventory.repository.EventRepository;
import com.example.inventory.repository.VenueRepository;
import com.example.inventory.response.EventInventoryResponse;
import com.example.inventory.response.VenueInventoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventorySericeImpl implements InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    public InventorySericeImpl(EventRepository eventRepository,
                               VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public List<EventInventoryResponse> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventInventoryResponse::fromEntity).toList();
    }

    @Override
    public Optional<VenueInventoryResponse> getVenueInformation(Long venueId) {
        return venueRepository.findById(venueId).map(VenueInventoryResponse::fromEntity);
    }

}
