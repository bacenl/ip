/**
 * Represents a command to delete a task
 */
class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a delete command with the specified task index
     *
     * @param index the index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the specified task
     *
     * @param tasks the task list to remove from
     * @param ui the user interface for displaying results
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task removedTask = tasks.remove(index);
            ui.printDeleteTask(removedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Invalid task number. Current list size is " + tasks.size());
        }
    }
}
