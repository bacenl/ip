import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent Event
 */
public class Event extends Task {
    private String from;
    private String to;


    /**
     * Initialize Event with name and isDone = false
     */
    public Event(String name, String from, String to) {
        setName(name);
        setIsDone(false);
        this.from = from;
        this.to = to;
    }

    /**
     * Initialize Event with name and isDone
     */
    @JsonCreator
    public Event(@JsonProperty("name") String name,
                @JsonProperty("isDone") boolean isDone,
                @JsonProperty("from") String from,
                @JsonProperty("to") String to) {
        setName(name);
        setIsDone(isDone);
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
