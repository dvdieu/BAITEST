package domain.model.bill;

import java.math.BigDecimal;

public class BillingHistory {
    String id;
    BigDecimal amount;
    EState state;

    public BillingHistory(String id, BigDecimal amount, EState state) {
        this.id = id;
        this.amount = amount;
        this.state = state;
    }

    @Override
    public String toString() {
        return "BillingHistory{" +
                "id=" + id +
                ", amount=" + amount +
                ", state=" + state +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public EState getState() {
        return state;
    }

    public void setState(EState state) {
        this.state = state;
    }
}
