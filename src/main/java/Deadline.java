import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent Deadline
 */
public class Deadline extends Task {
    private String due;

    public Deadline() {
    }

    /**
     * Initialize Deadline with name and isDone = false
     */
    public Deadline(String name, String due) {
        setName(name);
        setIsDone(false);
        this.due = due;
    }

    /**
     * Initialize Deadline with name and isDone
     */
    @JsonCreator
    public Deadline(@JsonProperty("name") String name,
                   @JsonProperty("isDone") boolean isDone,
                   @JsonProperty("due") String due) {
        setName(name);
        setIsDone(isDone);
        this.due = due;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), due);
    }
}
