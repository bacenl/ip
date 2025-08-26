package logic.commands;

import models.TaskList;
import ui.Ui;

/**
 * Represents a command to find a keyword
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a mark command with the specified task index and mark status
     *
     * @param index  the index of the task to mark/unmark
     * @param isDone true to mark as done, false to unmark
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the mark command by updating the specified task's status
     *
     * @param tasks the task list to update
     * @param ui    the user interface for displaying results
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            ui.printFind(tasks.filterTasksByKeyword(keyword));
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Invalid task number. Current list size is " + tasks.size());
        }
    }
}
