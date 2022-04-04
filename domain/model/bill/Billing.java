package domain.model.bill;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Billing implements Serializable {
    String id;
    EBillType type;
    BigDecimal amount;
    Date dueDate;
    EState state;
    EPROVIDER provider;
    List<BillingHistory> history;
    Long walletId;

    public Billing(String id, EBillType type, BigDecimal amount, Date dueDate, EPROVIDER provider, EState state, Long walletId) {
        this.id = id;
        this.provider = provider;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.state = state;
        this.history = new ArrayList<>();
        history.add(new BillingHistory(id, amount, state));
        this.walletId = walletId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EBillType getType() {
        return type;
    }

    public void setType(EBillType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public EState getState() {
        return state;
    }

    public void setState(EState state) {
        this.state = state;
    }

    public List<BillingHistory> getHistory() {
        return history;
    }

    public void setHistory(List<BillingHistory> history) {
        this.history = history;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }
}
