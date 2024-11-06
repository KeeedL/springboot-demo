package com.gbocquet.fundsprocessor.service.processor;

import com.gbocquet.fundsprocessor.service.processor.withdrawal.DefaultWithdrawalProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultWithdrawalProcessorTest {

    private final DefaultWithdrawalProcessor withdrawalProcessor = new DefaultWithdrawalProcessor();

    @Test
    void processWithdrawalShouldReturnExpectedAmount() {
        // WHEN
        final var result = withdrawalProcessor.processWithdrawalAmount(20.5f, 12.5f);

        // THEN
        assertEquals(8, result);
    }
}
