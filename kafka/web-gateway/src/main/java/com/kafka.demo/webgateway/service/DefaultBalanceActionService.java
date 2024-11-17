package com.kafka.demo.webgateway.service;

import com.kafka.demo.webgateway.infra.SendBalanceActionProcessor;
import org.springframework.stereotype.Service;
import transfer.money.request.TransferMoneyRequestDto;

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
