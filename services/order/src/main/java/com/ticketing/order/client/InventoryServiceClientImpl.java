package com.ticketing.order.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class InventoryServiceClientImpl implements InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Override
    public ResponseEntity<Void> updateInventory(Long eventId, Long ticketsBooked) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(inventoryServiceUrl + "/events/" + eventId + "/capacity/" + ticketsBooked, null);
        return ResponseEntity.ok().build();
    }
}
