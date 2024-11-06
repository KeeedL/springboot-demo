package com.gbocquet.fundsprocessor.infra.kafka;

import com.gbocquet.fundsprocessor.dto.request.TransferMoneyRequestDto;
import com.gbocquet.fundsprocessor.infra.kafka.TransferMoneyEventListener;
import com.gbocquet.fundsprocessor.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransferMoneyEventListenerTest {

    @InjectMocks
    TransferMoneyEventListener transferMoneyEventListener;

    @Mock
    AccountService accountService;

    @Test
    void onTransferMoneyEventShouldCallService() {
        // GIVEN
        final var request = new TransferMoneyRequestDto("source", "target", 12.6f);

        // WHEN
        transferMoneyEventListener.listenTransferMoneyEvent(request);

        // THEN
        verify(accountService, times(1)).transferMoneyToOtherAccount(request);
    }
}
