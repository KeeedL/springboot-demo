package com.kafka.demo.fundsprocessor.service;

import com.kafka.demo.fundsprocessor.dto.AccountDto;
import com.kafka.demo.fundsprocessor.mapper.AccountMapper;
import com.kafka.demo.fundsprocessor.repository.AccountRepository;
import com.kafka.demo.fundsprocessor.service.processor.deposit.DepositProcessor;
import com.kafka.demo.fundsprocessor.service.processor.withdrawal.WithdrawalProcessor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import transfer.money.request.TransferMoneyRequestDto;

import java.util.Optional;

@Service
public class DefaultAccountService implements AccountService {
    private final AccountRepository repository;
    private final AccountMapper accountMapper;
    private final DepositProcessor depositProcessor;
    private final WithdrawalProcessor withdrawalProcessor;

    public DefaultAccountService(AccountRepository repository, AccountMapper accountMapper, DepositProcessor depositProcessor, WithdrawalProcessor withdrawalProcessor) {
        this.repository = repository;
        this.accountMapper = accountMapper;
        this.depositProcessor = depositProcessor;
        this.withdrawalProcessor = withdrawalProcessor;
    }

    @Override
    public Optional<AccountDto> getAccountByUuid(final String accountUuid) {
        return repository.findByUuid(accountUuid).map(accountMapper::toDto);
    }

    @Override
    public Optional<AccountDto> depositMoneyOnAccount(final String accountUuid, final float amount) {
        return updateAccountBalance(accountUuid, amount, true);
    }

    @Override
    public Optional<AccountDto> withdrawMoneyOnAccount(final String accountUuid, final float amount) {
        return updateAccountBalance(accountUuid, amount, false);
    }

    @Override
    @Transactional
    public Optional<AccountDto> transferMoneyToOtherAccount(final TransferMoneyRequestDto request) {

        if(repository.existsByUuid(String.valueOf(request.getSourceAccountUuid())) &&
                repository.existsByUuid(String.valueOf(request.getTargetAccountUuid()))) {
            // Withdraw money to source account
            final var sourceAccountUpdated = updateAccountBalance(String.valueOf(request.getSourceAccountUuid()), request.getAmountToTransfer(), false);

            // Deposit money to the target account
            updateAccountBalance(String.valueOf(request.getTargetAccountUuid()), request.getAmountToTransfer(), true);

            return sourceAccountUpdated;
        }
        return Optional.empty();
    }

    private Optional<AccountDto> updateAccountBalance(final String accountUuid, final float amount, boolean isDeposit) {
        return repository.findByUuid(accountUuid).map(account -> {
            float newAmount = isDeposit ?
                    depositProcessor.processDepositAmount(account.getAmount(), amount) :
                    withdrawalProcessor.processWithdrawalAmount(account.getAmount(), amount);
            account.setAmount(newAmount);
            repository.save(account);
            return accountMapper.toDto(account);
        });
    }
}
