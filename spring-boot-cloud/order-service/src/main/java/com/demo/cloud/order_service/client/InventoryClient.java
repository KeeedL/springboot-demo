package com.demo.cloud.order_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @CircuitBreaker(name = "inventory-service", fallbackMethod = "fallbackUpdate")
    @RequestMapping(method = RequestMethod.PUT, value = "${client.inventory-service.endpoints.stock}", consumes = "application/json")
    String update(@RequestBody Integer nb);

    default String fallbackUpdate(Integer nb, Exception e) {
        return "Inventory service not available - Fallback method used to update stock (kafka message)";
    }
}