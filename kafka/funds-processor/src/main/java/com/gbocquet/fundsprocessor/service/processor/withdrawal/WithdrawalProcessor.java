package com.gbocquet.fundsprocessor.service.processor.withdrawal;

public interface WithdrawalProcessor {
    float processWithdrawalAmount(float currentAmount, float amountToWithdraw);
}
