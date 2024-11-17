package com.kafka.demo.webgateway.infra;

import transfer.money.request.TransferMoneyRequestDto;

public interface SendBalanceActionProcessor {
    void sendMessage(TransferMoneyRequestDto request);
}
