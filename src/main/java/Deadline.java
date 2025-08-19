/**
 * Class to represent Deadline
 */
public class Deadline extends Task {
    private String due;

    /**
     * Initialize Deadline with name and isDone = false
     */
    public Deadline(String name, String due) {
        super(name);
        this.due = due;
    }

    /**
     * Initialize Deadline with name and isDone
     */
    public Deadline(String name, boolean isDone, String due) {
        super(name, isDone);
        this.due = due;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), due);
    }
}
