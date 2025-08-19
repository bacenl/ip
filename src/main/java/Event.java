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
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Initialize Event with name and isDone
     */
    public Event(String name, boolean isDone, String from, String to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
