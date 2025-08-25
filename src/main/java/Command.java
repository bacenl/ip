/**
 * Represents the type of command that can be executed
 */
enum CommandType {
    EXIT, LIST, DELETE, MARK, ADD_TODO, ADD_DEADLINE, ADD_EVENT
}

/**
 * Represents a command that can be executed by the chatbot
 */
public abstract class Command {
    /**
     * Executes the command with the given task list and UI
     *
     * @param tasks the task list to operate on
     * @param ui the user interface for displaying results
     */
    public abstract void execute(TaskList tasks, Ui ui);
}
