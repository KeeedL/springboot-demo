package com.gbocquet.fundsprocessor.service.processor;

import com.gbocquet.fundsprocessor.service.processor.deposit.DefaultDepositProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultDepositProcessorTest {
    private final DefaultDepositProcessor depositProcessor = new DefaultDepositProcessor();

    @Test
    void processDepositShouldReturnExpectedAmount() {
        // WHEN
        final var result = depositProcessor.processDepositAmount(10.5f, 12.5f);

        // THEN
        assertEquals(23, result);
    }
}
