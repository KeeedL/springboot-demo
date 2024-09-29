package com.demo.cloud.inventory_service.supplier;

import com.demo.cloud.inventory_service.client.supplier.SecondSupplierClient;
import com.demo.cloud.inventory_service.client.supplier.SupplierClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecondSupplierClientTest {

    private final SupplierClient supplierClient = new SecondSupplierClient();

    @Test
    void restockingShouldContainsFirst() {
        // Given

        // When
        final var result = supplierClient.restocking(2);

        // Then
        assertTrue(result.contains("second"));
    }
}
