package com.demo.cloud.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("inventory-service")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.PUT, value = "${client.inventory-service.endpoints.stock}", consumes = "application/json")
    String update(@RequestBody Integer nb);
}