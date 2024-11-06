package com.gbocquet.webgateway.service;


import com.gbocquet.webgateway.dto.TransferMoneyRequestDto;
import com.gbocquet.webgateway.infra.SendBalanceActionProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultBalanceActionServiceTest {

    @InjectMocks
    DefaultBalanceActionService balanceActionService;

    @Mock
    SendBalanceActionProcessor sendBalanceActionProcessor;

    @Test
    void transferMoneyShouldCallSendBalanceAction() {
        // GIVEN
        final var transferRequest = new TransferMoneyRequestDto("source", "target", 12.5f);

        // WHEN
        balanceActionService.transferMoney(transferRequest);

        // THEN
        verify(sendBalanceActionProcessor, times(1)).sendMessage(transferRequest);

    }
}