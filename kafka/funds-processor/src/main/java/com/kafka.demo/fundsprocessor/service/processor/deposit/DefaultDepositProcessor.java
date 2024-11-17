package com.kafka.demo.fundsprocessor.service.processor.deposit;

import org.springframework.stereotype.Service;

@Service
public class DefaultDepositProcessor implements DepositProcessor {
    @Override
    public float processDepositAmount(float currentAmount, float amountToAdd) {
        // TODO: Should have validation rules (check non-negative float...)  and processing rules (taxes...)
        return currentAmount + amountToAdd;
    }
}
