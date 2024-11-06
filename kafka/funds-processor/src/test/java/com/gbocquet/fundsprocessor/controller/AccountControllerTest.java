package com.gbocquet.fundsprocessor.controller;

import com.gbocquet.fundsprocessor.dto.AccountDto;
import com.gbocquet.fundsprocessor.dto.request.TransferMoneyRequestDto;
import com.gbocquet.fundsprocessor.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    private final String UUID = "uuid";
    private final float AMOUNT = 10.5f;
    private final AccountDto MOCKED_ACCOUNT_DTO = new AccountDto(UUID, AMOUNT);

    @Test
    void getAccountByUnknownUuidShouldReturnNotFound() {
        // GIVEN
        when(accountService.getAccountByUuid(any())).thenReturn(Optional.empty());

        // WHEN
        final var result = accountController.getAccountByUuid(UUID);

        // THEN
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatusCode().value());
        verify(accountService, times(1)).getAccountByUuid(UUID);
    }

    @Test
    void getAccountByKnownUuidShouldReturnOk() {
        // GIVEN
        when(accountService.getAccountByUuid(any())).thenReturn(Optional.of(MOCKED_ACCOUNT_DTO));

        // WHEN
        final var result = accountController.getAccountByUuid(UUID);

        // THEN
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        verify(accountService, times(1)).getAccountByUuid(UUID);
        assertEquals(MOCKED_ACCOUNT_DTO, result.getBody());
    }

    @Test
    void depositAccountByUnknownUuidShouldReturnEmpty() {
        // GIVEN
        when(accountService.depositMoneyOnAccount(any(), anyFloat())).thenReturn(Optional.empty());

        // WHEN
        final var result = accountController.depositMoneyOnAccountByUuid(UUID, AMOUNT);

        // THEN
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatusCode().value());
        verify(accountService, times(1)).depositMoneyOnAccount(UUID, AMOUNT);
    }

    @Test
    void depositAccountByKnownUuidShouldReturnOk() {
        // GIVEN
        when(accountService.depositMoneyOnAccount(any(), anyFloat())).thenReturn(Optional.of(MOCKED_ACCOUNT_DTO));

        // WHEN
        final var result = accountController.depositMoneyOnAccountByUuid(UUID, AMOUNT);

        // THEN
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        verify(accountService, times(1)).depositMoneyOnAccount(UUID, AMOUNT);
        assertEquals(MOCKED_ACCOUNT_DTO, result.getBody());
    }

    @Test
    void withdrawAccountByUnknownUuidShouldReturnEmpty() {
        // GIVEN
        when(accountService.withdrawMoneyOnAccount(any(), anyFloat())).thenReturn(Optional.empty());

        // WHEN
        final var result = accountController.withdrawMoneyOnAccountByUuid(UUID, AMOUNT);

        // THEN
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatusCode().value());
        verify(accountService, times(1)).withdrawMoneyOnAccount(UUID, AMOUNT);
    }

    @Test
    void withdrawAccountByKnownUuidShouldReturnOk() {
        // GIVEN
        when(accountService.withdrawMoneyOnAccount(any(), anyFloat())).thenReturn(Optional.of(MOCKED_ACCOUNT_DTO));

        // WHEN
        final var result = accountController.withdrawMoneyOnAccountByUuid(UUID, AMOUNT);

        // THEN
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        verify(accountService, times(1)).withdrawMoneyOnAccount(UUID, AMOUNT);
        assertEquals(MOCKED_ACCOUNT_DTO, result.getBody());
    }

    @Test
    void transferAccountByUnknownUuidShouldReturnEmpty() {
        // GIVEN
        final var targetAccountUuid = "uuid2";
        final var transferMoneyRequest = new TransferMoneyRequestDto(UUID, targetAccountUuid, AMOUNT);
        when(accountService.transferMoneyToOtherAccount(any())).thenReturn(Optional.empty());

        // WHEN
        final var result = accountController.transferMoneyOnAccountByUuid(transferMoneyRequest);

        // THEN
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatusCode().value());
        verify(accountService, times(1)).transferMoneyToOtherAccount(transferMoneyRequest);
    }

    @Test
    void transferAccountByKnownUuidShouldReturnOk() {
        // GIVEN
        final var targetAccountUuid = "uuid2";
        final var transferMoneyRequest = new TransferMoneyRequestDto(UUID, targetAccountUuid, AMOUNT);
        when(accountService.transferMoneyToOtherAccount(any())).thenReturn(Optional.of(MOCKED_ACCOUNT_DTO));

        // WHEN
        final var result = accountController.transferMoneyOnAccountByUuid(transferMoneyRequest);

        // THEN
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        verify(accountService, times(1)).transferMoneyToOtherAccount(transferMoneyRequest);
        assertEquals(MOCKED_ACCOUNT_DTO, result.getBody());
    }
}
