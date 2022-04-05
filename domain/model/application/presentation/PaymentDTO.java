package domain.model.application.presentation;

import domain.model.mservices.EPaymentState;
import domain.model.mservices.Payment;

import java.math.BigDecimal;

public class PaymentDTO {
    String id;
    String billingId;
    BigDecimal amount;
    String walletId;
    Long paymentDate;
    EPaymentState paymentState;

    public PaymentDTO(String id, String billingId, BigDecimal amount, String walletId, Long paymentDate, EPaymentState paymentState) {
        this.id = id;
        this.billingId = billingId;
        this.amount = amount;
        this.walletId = walletId;
        this.paymentDate = paymentDate;
        this.paymentState = paymentState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public Long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Long paymentDate) {
        this.paymentDate = paymentDate;
    }

    public EPaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(EPaymentState paymentState) {
        this.paymentState = paymentState;
    }

    public static PaymentDTO from(Payment payment) {
        return new PaymentDTOBuilderImpl()
                .setAmount(payment.getAmount())
                .setPaymentDate(payment.getPaymentDate())
                .setPaymentState(payment.getPaymentState())
                .setBillingId(payment.getBillingId())
                .setWalletId(payment.getWalletId())
                .build();
    }
}


