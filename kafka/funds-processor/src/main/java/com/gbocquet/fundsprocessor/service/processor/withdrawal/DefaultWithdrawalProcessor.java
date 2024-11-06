package com.gbocquet.fundsprocessor.service.processor.withdrawal;

import org.springframework.stereotype.Service;

@Service
public class DefaultWithdrawalProcessor implements WithdrawalProcessor {
    @Override
    public float processWithdrawalAmount(float currentAmount, float amountToWithdraw) {
        // TODO: Should have validation rules (check non-negative float...)  and processing rules (fees...)
        return currentAmount - amountToWithdraw;
    }
}
