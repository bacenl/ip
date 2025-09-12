package logic.commands;

import models.TaskList;
import models.ToDo;
import ui.Ui;

/**
 * Represents a command to add a todo task
 */
public class ToDoCommand extends Command {
    private String name;

    /**
     * Constructs a todo command with the specified task name
     *
     * @param name the description of the todo task
     */
    public ToDoCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the todo command by adding a new todo task
     *
     * @param tasks the task list to add to
     * @param ui    the user interface for displaying results
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        ToDo todo = new ToDo(name);
        tasks.add(todo);
        return ui.getAddTaskResponse(todo, tasks.size());
    }
}
