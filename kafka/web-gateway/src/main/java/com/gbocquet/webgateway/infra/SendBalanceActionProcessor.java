package com.gbocquet.webgateway.infra;

import com.gbocquet.webgateway.dto.TransferMoneyRequestDto;

public interface SendBalanceActionProcessor {
    void sendMessage(TransferMoneyRequestDto request);
}
