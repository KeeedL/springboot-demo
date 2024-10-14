package com.demo.cloud.inventory_service.controller;

import com.demo.cloud.inventory_service.client.supplier.SupplierClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
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
        verify(supplierClient, times(1)).restocking(anyInt());
        assertTrue(Objects.requireNonNull(result.getBody()).contains(nbInput.toString()));
        assertTrue(Objects.requireNonNull(result.getBody()).contains(mockedSupplierResult));
    }

    // TODO: add parameterized tests + argument captor

    // Parameterized test
    private static Stream<Arguments> provideStringsForValidation() {
        return Stream.of(
                Arguments.of("validString", true),   // Valid string
                Arguments.of("12345", false),        // Invalid string (only digits)
                Arguments.of("test@123", false),     // Invalid string (contains special characters)
                Arguments.of("helloWorld", true),    // Valid string
                Arguments.of("", false),              // Invalid string (empty)
                Arguments.of(null, false)             // Invalid string (null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForValidation")
    void testIsValid(String input, boolean expected) {
        assertEquals(expected, inventoryController.isValid(input));
    }

    @Test
    void testArgumentCaptor() {
        // GIVEN
        final var input = 2;
        when(supplierClient.restocking(anyInt())).thenReturn("Mocked response");

        // WHEN
        inventoryController.updateStockCustomMethod(input);

        // THEN
        final var captor = ArgumentCaptor.forClass(Integer.class);
        verify(supplierClient, times(1)).restocking(captor.capture());
        assertEquals(input + 10, captor.getValue());
    }


}
