package logic.commands;

import java.time.LocalDate;

import models.Event;
import models.TaskList;
import ui.Ui;

/**
 * Represents a command to add an event task
 */
public class EventCommand extends Command {
    private String name;
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an event command with the specified task name and date range
     *
     * @param name the description of the event task
     * @param from the start date of the event
     * @param to the end date of the event
     */
    public EventCommand(String name, LocalDate from, LocalDate to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by adding a new event task
     *
     * @param tasks the task list to add to
     * @param ui the user interface for displaying results
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Event event = new Event(name, from, to);
        tasks.add(event);
        ui.printAddTask(event, tasks.size());
    }
}
