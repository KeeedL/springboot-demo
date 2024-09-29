package com.demo.cloud.inventory_service.supplier;

import com.demo.cloud.inventory_service.client.supplier.FirstSupplierClient;
import com.demo.cloud.inventory_service.client.supplier.SupplierClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstSupplierClientTest {

    private final SupplierClient supplierClient = new FirstSupplierClient();

    @Test
    void restockingShouldContainsFirst() {
        // Given
        // When
        final var result = supplierClient.restocking(2);

        // Then
        assertTrue(result.contains("first"));
    }
}
