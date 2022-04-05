package domain.model.mservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Payment implements Serializable {
    String id;
    String billingId;
    BigDecimal amount;
    String walletId;
    Long paymentDate;
    EPaymentState paymentState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(getId(), payment.getId()) && Objects.equals(getBillingId(), payment.getBillingId()) && Objects.equals(getAmount(), payment.getAmount()) && Objects.equals(getWalletId(), payment.getWalletId()) && Objects.equals(getPaymentDate(), payment.getPaymentDate()) && getPaymentState() == payment.getPaymentState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBillingId(), getAmount(), getWalletId(), getPaymentDate(), getPaymentState());
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

    public Payment(String id, String billingId, BigDecimal amount, String walletId, Long paymentDate) {
        this.id = id;
        this.billingId = billingId;
        this.amount = amount;
        this.walletId = walletId;
        this.paymentDate = paymentDate;
        this.paymentState = EPaymentState.CREATED;
    }
}
