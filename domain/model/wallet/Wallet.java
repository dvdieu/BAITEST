package domain.model.wallet;

import core.WalletPaymentException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Wallet implements Serializable {
    private long id;
    private BigDecimal balance;
    List<Event> events;

    public Wallet(long id, long balance) {
        this.id = id;
        this.balance = BigDecimal.valueOf(balance);
        this.events = new ArrayList<>();
        events.add(new Event(System.nanoTime(), System.currentTimeMillis(), EEvent.INIT));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Wallet cashIn(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        this.events.add(new Event(System.nanoTime(), System.currentTimeMillis(), EEvent.CASH_IN));
        return this;
    }

    public Wallet payment(BigDecimal amount) throws WalletPaymentException {
        if (this.getBalance().longValue() < amount.longValue()) {
            throw new WalletPaymentException("Sorry! Not enough fund to proceed with payment.");
        }
        this.balance = this.balance.subtract(amount);
        this.events.add(new Event(System.nanoTime(), System.currentTimeMillis(), EEvent.PAYMENT));
        return this;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", balance=" + balance +
                ", events=" + events +
                '}';
    }
}
