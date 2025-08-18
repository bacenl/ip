/**
 * Class to represent Task
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Initialize Task with name and isDone = false
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Initialize Task with name and isDone
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
