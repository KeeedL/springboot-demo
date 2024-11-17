package com.kafka.demo.webgateway.service;

import transfer.money.request.TransferMoneyRequestDto;

public interface BalanceActionService {
    void transferMoney(final TransferMoneyRequestDto request);
}
