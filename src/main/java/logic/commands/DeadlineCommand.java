package logic.commands;

import java.time.LocalDate;

import models.Deadline;
import models.TaskList;
import ui.Ui;

/**
 * Represents a command to add a deadline task
 */
public class DeadlineCommand extends Command {
    private String name;
    private LocalDate due;

    /**
     * Constructs a deadline command with the specified task name and due date
     *
     * @param name the description of the deadline task
     * @param due the due date for the deadline
     */
    public DeadlineCommand(String name, LocalDate due) {
        this.name = name;
        this.due = due;
    }

    /**
     * Executes the deadline command by adding a new deadline task
     *
     * @param tasks the task list to add to
     * @param ui the user interface for displaying results
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Deadline deadline = new Deadline(name, due);
        tasks.add(deadline);
        ui.printAddTask(deadline, tasks.size());
    }
}
