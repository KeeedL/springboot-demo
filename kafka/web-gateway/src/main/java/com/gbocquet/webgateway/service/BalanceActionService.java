package com.gbocquet.webgateway.service;

import com.gbocquet.webgateway.dto.TransferMoneyRequestDto;

public interface BalanceActionService {
    void transferMoney(final TransferMoneyRequestDto request);
}
