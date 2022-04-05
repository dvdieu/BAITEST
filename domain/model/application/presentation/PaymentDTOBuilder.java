package domain.model.application.presentation;

import domain.model.mservices.EPaymentState;

import java.math.BigDecimal;

public interface PaymentDTOBuilder {
    PaymentDTOBuilder setId(String id);

    PaymentDTOBuilder setBillingId(String billingId);

    PaymentDTOBuilder setAmount(BigDecimal amount);

    PaymentDTOBuilder setWalletId(String walletId);

    PaymentDTOBuilder setPaymentDate(Long paymentDate);

    PaymentDTOBuilder setPaymentState(EPaymentState paymentState);

    PaymentDTO build();
}
