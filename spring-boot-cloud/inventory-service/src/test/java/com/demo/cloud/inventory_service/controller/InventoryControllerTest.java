package com.demo.cloud.inventory_service.controller;

import com.demo.cloud.inventory_service.client.supplier.SupplierClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventoryControllerTest {

    @InjectMocks
    InventoryController inventoryController;

    @Mock
    SupplierClient supplierClient;

    @Test
    void updateStockShouldReturnNewStockAndCallSupplier() {
        // Given
        final var mockedSupplierResult = "mocked result from supplier";
        final Integer nbInput = 2;
        when(supplierClient.restocking(nbInput)).thenReturn(mockedSupplierResult);

        // When
        final var result = inventoryController.updateStock(nbInput);

        // Then
        verify(supplierClient, timeout(1)).restocking(anyInt());
        assertTrue(Objects.requireNonNull(result.getBody()).contains(nbInput.toString()));
        assertTrue(Objects.requireNonNull(result.getBody()).contains(mockedSupplierResult));
    }
}
