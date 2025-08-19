/**
 * Class to represent ToDo
 */
public class ToDo extends Task {
    /**
     * Initialize ToDo with name and isDone = false
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Initialize ToDo with name and isDone
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
