/**
 * Represents a command to mark or unmark a task
 */
class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    /**
     * Constructs a mark command with the specified task index and mark status
     *
     * @param index the index of the task to mark/unmark
     * @param isDone true to mark as done, false to unmark
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Executes the mark command by updating the specified task's status
     *
     * @param tasks the task list to update
     * @param ui the user interface for displaying results
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            tasks.markTask(index, isDone);
            ui.printMarkTask(tasks.get(index), isDone);
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Invalid task number. Current list size is " + tasks.size());
        }
    }
}
