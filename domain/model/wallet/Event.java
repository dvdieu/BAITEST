package domain.model.wallet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Event implements Serializable {
    long id;
    long time;
    BigDecimal amount;
    EEvent event;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event1 = (Event) o;
        return getId() == event1.getId() && getTime() == event1.getTime() && Objects.equals(getAmount(), event1.getAmount()) && getEvent() == event1.getEvent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTime(), getAmount(), getEvent());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public EEvent getEvent() {
        return event;
    }

    public void setEvent(EEvent event) {
        this.event = event;
    }

    public Event(long id, long time, BigDecimal amount, EEvent event) {
        this.id = id;
        this.time = time;
        this.amount = amount;
        this.event = event;
    }
}
