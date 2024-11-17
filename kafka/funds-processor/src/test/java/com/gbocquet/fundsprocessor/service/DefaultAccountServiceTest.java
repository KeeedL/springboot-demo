package com.kafka.demo.fundsprocessor.service;

import com.kafka.demo.fundsprocessor.dto.AccountDto;
import com.kafka.demo.fundsprocessor.entity.AccountEntity;
import com.kafka.demo.fundsprocessor.mapper.AccountMapper;
import com.kafka.demo.fundsprocessor.repository.AccountRepository;
import com.kafka.demo.fundsprocessor.service.processor.deposit.DepositProcessor;
import com.kafka.demo.fundsprocessor.service.processor.withdrawal.WithdrawalProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import transfer.money.request.TransferMoneyRequestDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultAccountServiceTest {

    @InjectMocks
    DefaultAccountService accountService;

    @Mock
    AccountRepository repository;
    @Mock
    AccountMapper accountMapper;
    @Mock
    DepositProcessor depositProcessor;
    @Mock
    WithdrawalProcessor withdrawalProcessor;

    private final String UUID = "uuid";
    private final float AMOUNT = 10.5f;
    private final AccountDto MOCKED_ACCOUNT_DTO = new AccountDto(UUID, AMOUNT);
    private final AccountEntity MOCKED_ACCOUNT_ENTITY = new AccountEntity(1L, UUID, AMOUNT);

    @Test
    void getAccountByUuidShouldReturnEmpty() {
        // GIVEN
        when(repository.findByUuid(any())).thenReturn(Optional.empty());

        // WHEN
        final var result = accountService.getAccountByUuid(UUID);

        // THEN
        assertTrue(result.isEmpty());
        verify(repository, times(1)).findByUuid(UUID);
        verify(accountMapper, never()).toDto(any());
    }

    @Test
    void getAccountByUuidShouldReturnObject() {
        // GIVEN
        when(repository.findByUuid(any())).thenReturn(Optional.of(MOCKED_ACCOUNT_ENTITY));
        when(accountMapper.toDto(any())).thenReturn(MOCKED_ACCOUNT_DTO);

        // WHEN
        final var result = accountService.getAccountByUuid(UUID);

        // THEN
        assertTrue(result.isPresent());
        verify(repository, times(1)).findByUuid(UUID);
        verify(accountMapper, times(1)).toDto(MOCKED_ACCOUNT_ENTITY);
        assertEquals(MOCKED_ACCOUNT_DTO, result.get());
    }

    @Test
    void depositMoneyOnAccount() {
        // TODO
    }

    @Test
    void withdrawMoneyOnAccount() {
        // TODO
    }

    @Test
    void transferMoneyToOtherAccount() {
        // GIVEN
        final var mockedProcessorAmount = 12f;
        final var targetAccountUuid = "uuid2";
        final var transferMoneyRequest = new TransferMoneyRequestDto(UUID, targetAccountUuid, AMOUNT);
        when(repository.existsByUuid(any())).thenReturn(true);
        when(repository.findByUuid(any())).thenReturn(Optional.of(MOCKED_ACCOUNT_ENTITY));
        when(depositProcessor.processDepositAmount(anyFloat(), anyFloat())).thenReturn(mockedProcessorAmount);
        when(withdrawalProcessor.processWithdrawalAmount(anyFloat(), anyFloat())).thenReturn(mockedProcessorAmount);
        when(repository.save(any())).thenReturn(MOCKED_ACCOUNT_ENTITY);
        when(accountMapper.toDto(any())).thenReturn(MOCKED_ACCOUNT_DTO);

        // WHEN
        final var result = accountService.transferMoneyToOtherAccount(transferMoneyRequest);

        // THEN
        verify(repository, times(1)).existsByUuid(String.valueOf(transferMoneyRequest.getSourceAccountUuid()));
        verify(repository, times(1)).existsByUuid(String.valueOf(transferMoneyRequest.getTargetAccountUuid()));
        verify(repository, times(1)).findByUuid(String.valueOf(transferMoneyRequest.getSourceAccountUuid()));
        verify(repository, times(1)).findByUuid(String.valueOf(transferMoneyRequest.getTargetAccountUuid()));
        verify(depositProcessor, times(1)).processDepositAmount(MOCKED_ACCOUNT_ENTITY.getAmount(), transferMoneyRequest.getAmountToTransfer());
        verify(withdrawalProcessor, times(1)).processWithdrawalAmount(AMOUNT, transferMoneyRequest.getAmountToTransfer());
        verify(repository, times(2)).save(MOCKED_ACCOUNT_ENTITY);
        verify(accountMapper, times(2)).toDto(MOCKED_ACCOUNT_ENTITY);

        final var entityCaptor = ArgumentCaptor.forClass(AccountEntity.class);
        verify(repository, times(2)).save(entityCaptor.capture());
        final var entityArgCaptured = entityCaptor.getValue();
        assertEquals(mockedProcessorAmount, entityArgCaptured.getAmount());
        assertEquals(MOCKED_ACCOUNT_ENTITY.getId(), entityArgCaptured.getId());
        assertEquals(MOCKED_ACCOUNT_ENTITY.getUuid(), entityArgCaptured.getUuid());
        assertTrue(result.isPresent());
        assertEquals(MOCKED_ACCOUNT_DTO, result.get());
    }
}