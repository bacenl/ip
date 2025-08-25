import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Yapper class is the Chatbot for CS2103T.
 */
public class Yapper {
    private Scanner inputScanner;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Constructs a new Yapper chatbot instance.
     */
    public Yapper() {
        inputScanner = new Scanner(System.in);

        tasks = FileManager.loadTasks();
        printGreet();
        listenForCommand();
    }

    // Chatbot Behaviour
    private void printGreet() {
        System.out.println("Hello! I'm Yapper");
        System.out.println("What can I do for you?");
    }

    private void printExit() {
        saveTasks();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    private void markTask(String command, boolean isDone) throws InvalidCommandException {
        Pattern markPattern = Pattern.compile("^(mark|unmark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(command);
        if (markMatcher.matches()) {
            String digits = markMatcher.group(2);
            int index = Integer.parseInt(digits) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new InvalidCommandException("Invalid task number. Current list size is " + tasks.size());
            }
            Task task = tasks.get(index);
            task.setIsDone(isDone);
            if (isDone) {
                System.out.println("Nice! I have marked this task as done:");
            } else {
                System.out.println("Ok, I have marked this task as not done yet:");
            }
            System.out.println(task);
        } else {
            throw new InvalidCommandException("Please ensure the following format 'mark / unmark (positive integer)'");
        }
    }

    private void deleteTask(String command) throws InvalidCommandException {
        Pattern markPattern = Pattern.compile("^delete (\\d+)$");
        Matcher markMatcher = markPattern.matcher(command);
        if (markMatcher.matches()) {
            String digits = markMatcher.group(1);
            int index = Integer.parseInt(digits) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new InvalidCommandException("Invalid task number. Current list size is " + tasks.size());
            }
            Task task = tasks.get(index);
            tasks.remove(task);
            System.out.println("Noted. I have removed this task:");
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        } else {
            throw new InvalidCommandException("Please ensure the following format 'delete (positive integer)'");
        }
    }

    private void addToDo(String command) throws InvalidCommandException {
        String name = command.substring(5).trim();
        if (name.isEmpty()) {
            throw new InvalidCommandException("Description of ToDo cannot be empty");
        }
        ToDo todo = new ToDo(name);
        System.out.println("Got it. I've added this task:");
        tasks.add(todo);
        System.out.println(todo);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    private void addDeadline(String command) throws InvalidCommandException {
        String[] parts = command.substring(9).trim().split("/by", 2);
        String name = parts[0].trim();
        String raw_by = parts[1].trim();

        if (parts.length != 2) {
            throw new InvalidCommandException("Need /by parameter");
        }
        if (name.isEmpty()) {
            throw new InvalidCommandException("Description of Deadline cannot be empty");
        }
        if (raw_by.isEmpty()) {
            throw new InvalidCommandException("/by of Deadline cannot be empty");
        }
        LocalDate by;
        try {
            by = LocalDate.parse(raw_by);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format");
        }
        Deadline deadline = new Deadline(name, by);
        System.out.println("Got it. I've added this task:");
        tasks.add(deadline);
        System.out.println(deadline);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    private void addEvent(String command) throws InvalidCommandException {
        String[] parts = command.substring(6).trim().split("/from|/to");
        if (parts.length != 3) {
            throw new InvalidCommandException("Need both /from and /to parameters");
        }
        boolean isFromFirst = command.contains("/from") && (command.indexOf("/from") < command.indexOf("/to"));
        String description = parts[0].trim();
        String raw_from = isFromFirst ? parts[1].trim() : parts[2].trim();
        String raw_to = isFromFirst ? parts[2].trim() : parts[1].trim();
        if (parts[0].isEmpty()) {
            throw new InvalidCommandException("Description of Event cannot be empty");
        }
        if (raw_from.isEmpty() || raw_to.isEmpty()) {
            throw new InvalidCommandException("/from or /to of Event cannot be empty");
        }
        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(raw_from);
            to = LocalDate.parse(raw_to);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format");
        }
        Event event = new Event(description, from, to);
        System.out.println("Got it. I've added this task:");
        tasks.add(event);
        System.out.println(event);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    private void saveTasks() {
        FileManager.saveTasks(tasks);
    }

    private void listenForCommand() {
        while (true) {
            String command = inputScanner.nextLine();

            try {
                if (command.equals("bye")) {
                    printExit();
                    break;
                } else if (command.equals("list")) {
                    printList();
                } else if (command.startsWith("delete ")) {
                    deleteTask(command);
                } else if (command.startsWith("mark ")) {
                    markTask(command, true);
                } else if (command.startsWith("unmark ")) {
                    markTask(command, false);
                } else if (command.startsWith("todo ")) {
                    addToDo(command);
                } else if (command.startsWith("deadline ")) {
                    addDeadline(command);
                } else if (command.startsWith("event ")) {
                    addEvent(command);
                } else {
                    throw new InvalidCommandException("Sorry, please enter a "
                        + " valid commmand (mark / unmark / todo / deadline / event / list)");
                }
            } catch (InvalidCommandException e) {
                System.out.println(e);
            }
        }
    }
    public static void main(String[] args) {
        Yapper yapper = new Yapper();
    }

}

class InvalidCommandException extends Exception {
    public InvalidCommandException(String error) {
        super(error);
    }
}
