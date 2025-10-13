package com.ticketing.booking.client;

import com.ticketing.booking.exception.ResourceNotFoundException;
import com.ticketing.common.response.EventInventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClientImpl implements InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Override
    public EventInventoryResponse getInventory(Long eventId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<EventInventoryResponse> inventoryResponseEntity =
                    restTemplate.getForEntity(inventoryServiceUrl + "/events/" + eventId, EventInventoryResponse.class);
            return inventoryResponseEntity.getBody();
        } catch (HttpClientErrorException.NotFound ex) {
            throw ResourceNotFoundException.eventNotFound(eventId);
        } catch (RestClientException ex) {
            throw new RuntimeException("Error connecting to inventory service.", ex);
            // todo : handle 4xx and 5xx errors using advices
        }
    }
}
