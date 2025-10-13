package com.ticketing.booking.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends BookingException {

    private final Long resourceId;
    private final String resourceType;

    public ResourceNotFoundException(String message) {
        super("RESOURCE_NOT_FOUND", message);
        this.resourceType = null;
        this.resourceId = null;
    }

    public ResourceNotFoundException(String message, Long resourceId) {
        super("RESOURCE_NOT_FOUND", message);
        this.resourceId = resourceId;
        this.resourceType = null;
    }

    public ResourceNotFoundException(String message, Long resourceId,  String resourceType) {
        super(resourceType.toUpperCase() + "_NOT_FOUND", message);
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    public static ResourceNotFoundException customerNotFound(Long resourceId){
        return new ResourceNotFoundException("Customer of id: " + resourceId + " not found.", resourceId, "Customer");
    }

    public static ResourceNotFoundException eventNotFound(Long resourceId){
        return new ResourceNotFoundException("Event of id: " + resourceId + " not found.", resourceId, "Event");
    }

    public static ResourceNotFoundException venueNotFound(Long resourceId){
        return new ResourceNotFoundException("Venue of id: " + resourceId + " not found.",  resourceId, "Venue");
    }
}
