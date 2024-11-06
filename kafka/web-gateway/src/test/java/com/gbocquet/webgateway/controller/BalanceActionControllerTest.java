package com.gbocquet.webgateway.controller;

import com.gbocquet.webgateway.dto.TransferMoneyRequestDto;
import com.gbocquet.webgateway.service.BalanceActionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BalanceActionControllerTest {

    @InjectMocks
    BalanceActionController balanceActionController;

    @Mock
    BalanceActionService balanceActionService;

    @Test
    void transferMoneyShouldCallServiceAndReturnAccepted() {
        // GIVEN
        final var transferRequest = new TransferMoneyRequestDto("source", "target", 12.5f);

        // WHEN
        final var result = balanceActionController.transferMoneyRequest(transferRequest);

        // THEN
        verify(balanceActionService, times(1)).transferMoney(transferRequest);
        assertEquals(HttpStatus.ACCEPTED.value(), result.getStatusCode().value());
    }
}