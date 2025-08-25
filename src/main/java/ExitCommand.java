/**
 * Represents an exit command to terminate the application
 */
class ExitCommand extends Command {
    /**
     * Executes the exit command by saving tasks and displaying exit message
     *
     * @param tasks the task list to save before exiting
     * @param ui the user interface for displaying exit message
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        FileManager.saveTasks(tasks.getTasks());
        ui.printExit();
    }
}
