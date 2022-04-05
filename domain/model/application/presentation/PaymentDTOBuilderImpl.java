package domain.model.application.presentation;

import domain.model.mservices.EPaymentState;

import java.math.BigDecimal;

public class PaymentDTOBuilderImpl implements PaymentDTOBuilder {
    String id;
    String billingId;
    BigDecimal amount;
    String walletId;
    Long paymentDate;
    EPaymentState paymentState;

    public PaymentDTOBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public PaymentDTOBuilder setBillingId(String billingId) {
        this.billingId = billingId;
        return this;
    }

    public PaymentDTOBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public PaymentDTOBuilder setWalletId(String walletId) {
        this.walletId = walletId;
        return this;
    }

    public PaymentDTOBuilder setPaymentDate(Long paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public PaymentDTOBuilder setPaymentState(EPaymentState paymentState) {
        this.paymentState = paymentState;
        return this;
    }

    public PaymentDTO build() {
        return new PaymentDTO(this.id, this.billingId, this.amount, this.walletId, this.paymentDate, this.paymentState);
    }
}
