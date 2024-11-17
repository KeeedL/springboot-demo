package com.kafka.demo.fundsprocessor.service.processor.deposit;

public interface DepositProcessor {
    float processDepositAmount(float currentAmount, float amountToAdd);
}
