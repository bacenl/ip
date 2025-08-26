package ui;

import java.util.List;
import java.util.Scanner;

import models.Task;
import models.TaskList;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Scanner inputScanner;

    /**
     * Constructs a new Ui instance with a scanner for user input
     */
    public Ui() {
        inputScanner = new Scanner(System.in);
    }

    /**
     * Displays the greeting message when the chatbot starts
     */
    public void printGreet() {
        System.out.println("Hello! I'm Yapper");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the exit message when the chatbot terminates
     */
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays all tasks in the task list
     *
     * @param tasks the TaskList containing tasks to display
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Displays the result of marking or unmarking a task
     *
     * @param task   the task that was marked/unmarked
     * @param isDone true if the task was marked as done, false if unmarked
     */
    public void printMarkTask(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I have marked this task as done:");
        } else {
            System.out.println("Ok, I have marked this task as not done yet:");
        }
        System.out.println(task);
    }

    /**
     * Displays the result of deleting a task
     *
     * @param task      the task that was deleted
     * @param taskCount the new total number of tasks after deletion
     */
    public void printDeleteTask(Task task, int taskCount) {
        System.out.println("Noted. I have removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    /**
     * Displays the result of adding a new task
     *
     * @param task      the task that was added
     * @param taskCount the new total number of tasks after addition
     */
    public void printAddTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    /**
     * Displays the result of adding a new task
     *
     * @param filteredTasks the task that was added
     */
    public void printFind(List<Task> filteredTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < filteredTasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, filteredTasks.get(i));
        }
    }

    /**
     * Displays an error message to the user
     *
     * @param errorMessage the error message to display
     */
    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Reads a command from the user input
     *
     * @return the user's input as a String
     */
    public String readCommand() {
        return inputScanner.nextLine();
    }

    /**
     * Closes the scanner and releases system resources
     */
    public void close() {
        inputScanner.close();
    }
}
