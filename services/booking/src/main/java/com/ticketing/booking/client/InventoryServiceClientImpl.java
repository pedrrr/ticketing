package com.ticketing.booking.client;

import com.ticketing.booking.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class InventoryServiceClientImpl implements InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Override
    public Optional<InventoryResponse> getInventory(Long eventId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InventoryResponse> inventoryResponseEntity = restTemplate
                .getForEntity(inventoryServiceUrl + "/events/" + eventId, InventoryResponse.class);

        return (inventoryResponseEntity.getStatusCode().is2xxSuccessful() &&
            inventoryResponseEntity.getBody() != null)
                ? Optional.of(inventoryResponseEntity.getBody())
                : Optional.empty();
    }
}
