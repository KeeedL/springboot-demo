package com.demo.cloud.inventory_service.controller;

import com.demo.cloud.inventory_service.client.supplier.SupplierClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "stock",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryController {

    private static final Logger log = LoggerFactory.getLogger(InventoryController.class); // TODO: add AOP logging
    final SupplierClient supplierClient;
    private Integer stock = 0; // should be a service

    @Value( "${eureka.instance.metadata-map.zone}" )
    private String region;

    public InventoryController(SupplierClient supplierClient) {
        this.supplierClient = supplierClient;
    }

    @PutMapping
    public ResponseEntity<String> updateStock(@RequestBody Integer nb) {
        // should call service with this business logic (db update, supplier call...)
        stock += nb;
        final var restockingResult = supplierClient.restocking(nb);
        log.info("Application called on region : {}", region);
        log.info(restockingResult);

        return ResponseEntity.status(HttpStatus.OK).body("Updated stock is " + stock + "\r\n" + restockingResult);
    }


    // Should not be there - there for testing purpose

    // custom method to show argument captor utility (test part)
    public void updateStockCustomMethod(Integer nb) {
        supplierClient.restocking(nb + 10); // 10 should go in constant file...
    }

    // custom method to show parameterized test utility (test part)
    public boolean isValid(String input) {
        return input != null && input.matches("[a-zA-Z]+");
    }
}
