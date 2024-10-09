package com.demo.cloud.order_service.controller;

import com.demo.cloud.order_service.client.InventoryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "order",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final InventoryClient inventoryClient;

    public OrderController(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @PostMapping
    public ResponseEntity<String> postOrder(@RequestBody Integer nb) {
        final var stockUpdated = inventoryClient.update(nb);
        final var result = "Order placed successfully\r\nClient response is : " + stockUpdated;
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
