package com.gbocquet.fundsprocessor.service;

import com.gbocquet.fundsprocessor.dto.AccountDto;
import com.gbocquet.fundsprocessor.dto.request.TransferMoneyRequestDto;

import java.util.Optional;

public interface AccountService {
    Optional<AccountDto> getAccountByUuid(String uuid);
    Optional<AccountDto> depositMoneyOnAccount(String accountUuid, float amountToAdd);
    Optional<AccountDto> withdrawMoneyOnAccount(String accountUuid, float amountToWithdraw);
    Optional<AccountDto> transferMoneyToOtherAccount(TransferMoneyRequestDto request);
}
