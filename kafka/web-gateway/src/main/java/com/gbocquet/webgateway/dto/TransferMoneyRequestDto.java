package com.gbocquet.webgateway.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serial;
import java.io.Serializable;

public class TransferMoneyRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -7381107982856763745L;
    private String sourceAccountUuid;
    private String targetAccountUuid;
    private float amountToTransfer;

    public TransferMoneyRequestDto(String sourceAccountUuid, String targetAccountUuid, float amountToTransfer) {
        this.sourceAccountUuid = sourceAccountUuid;
        this.targetAccountUuid = targetAccountUuid;
        this.amountToTransfer = amountToTransfer;
    }

    public TransferMoneyRequestDto() {
    }

    public String getSourceAccountUuid() {
        return sourceAccountUuid;
    }

    public void setSourceAccountUuid(String sourceAccountUuid) {
        this.sourceAccountUuid = sourceAccountUuid;
    }

    public String getTargetAccountUuid() {
        return targetAccountUuid;
    }

    public void setTargetAccountUuid(String targetAccountUuid) {
        this.targetAccountUuid = targetAccountUuid;
    }

    public float getAmountToTransfer() {
        return amountToTransfer;
    }

    public void setAmountToTransfer(float amountToTransfer) {
        this.amountToTransfer = amountToTransfer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TransferMoneyRequestDto that = (TransferMoneyRequestDto) o;

        return new EqualsBuilder().append(amountToTransfer, that.amountToTransfer).append(sourceAccountUuid, that.sourceAccountUuid).append(targetAccountUuid, that.targetAccountUuid).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(sourceAccountUuid).append(targetAccountUuid).append(amountToTransfer).toHashCode();
    }

    @Override
    public String toString() {
        return "TransferMoneyDtoRequest{" +
                "sourceAccountUuid='" + sourceAccountUuid + '\'' +
                ", targetAccountUuid='" + targetAccountUuid + '\'' +
                ", amountToTransfer=" + amountToTransfer +
                '}';
    }
}
