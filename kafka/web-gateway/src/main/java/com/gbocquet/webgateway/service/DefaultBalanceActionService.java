package com.gbocquet.webgateway.service;

import com.gbocquet.webgateway.dto.TransferMoneyRequestDto;
import com.gbocquet.webgateway.infra.SendBalanceActionProcessor;
import org.springframework.stereotype.Service;

@Service
public class DefaultBalanceActionService implements BalanceActionService {

    private final SendBalanceActionProcessor sendBalanceActionProcessor;

    public DefaultBalanceActionService(SendBalanceActionProcessor sendBalanceActionProcessor) {
        this.sendBalanceActionProcessor = sendBalanceActionProcessor;
    }

    @Override
    public void transferMoney(final TransferMoneyRequestDto request) {
        // Could check if target account exists (so as not to pollute the kafka topic)
        sendBalanceActionProcessor.sendMessage(request);
    }
}
