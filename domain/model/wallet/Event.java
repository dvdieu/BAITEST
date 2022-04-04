package domain.model.wallet;

import java.io.Serializable;

public class Event implements Serializable {
    long id;
    long time;
    EEvent event;

    public Event(long id, long time, EEvent event) {
        this.id = id;
        this.time = time;
        this.event = event;
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

    public EEvent getEvent() {
        return event;
    }

    public void setEvent(EEvent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", time=" + time +
                ", event=" + event +
                '}';
    }
}
