package com.gbocquet.fundsprocessor.service.processor.deposit;

public interface DepositProcessor {
    float processDepositAmount(float currentAmount, float amountToAdd);
}
