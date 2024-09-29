package com.demo.cloud.inventory_service.client.supplier;

public class SecondSupplierClient implements SupplierClient {
    @Override
    public String restocking(final Integer nb) {
        return "Restocking using second supplier";
    }
}
