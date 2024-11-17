package com.kafka.demo.fundsprocessor.service;

import com.kafka.demo.fundsprocessor.dto.AccountDto;
import transfer.money.request.TransferMoneyRequestDto;

import java.util.Optional;

public interface AccountService {
    Optional<AccountDto> getAccountByUuid(String uuid);
    Optional<AccountDto> depositMoneyOnAccount(String accountUuid, float amountToAdd);
    Optional<AccountDto> withdrawMoneyOnAccount(String accountUuid, float amountToWithdraw);
    Optional<AccountDto> transferMoneyToOtherAccount(TransferMoneyRequestDto request);
}
