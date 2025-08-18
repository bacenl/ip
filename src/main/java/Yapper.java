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
        printGreet();
        listenForCommand();
    }

    // Chatbot Behaviour
    private void printGreet() {
        System.out.println("Hello! I'm Yapper");
        System.out.println("What can I do for you?");
    }

    private void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void appendToList(String input) {
        Task task = new Task(input);
        tasks.add(task);
        System.out.printf("added: %s\n", input);
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    private void markTask(String command, boolean isDone) {
        Pattern markPattern = Pattern.compile("^(mark|unmark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(command);
        if (markMatcher.matches()) {
            String digits = markMatcher.group(2);
            int index = Integer.parseInt(digits) - 1;
            Task task = tasks.get(index);
            task.setIsDone(isDone);
            if (isDone) {
                System.out.println("Nice! I have marked this task as done:");
            } else {
                System.out.println("Ok, I have marked this task as not done yet:");
            }
            System.out.println(task);
        }
    }

    private void listenForCommand() {
        while (true) {
            String command = inputScanner.nextLine();

            if (command.equals("bye")) {
                printExit();
                break;
            } else if (command.equals("list")) {
                printList();
            } else if (command.startsWith("mark ")) {
                markTask(command, true);
            } else if (command.startsWith("unmark ")) {
                markTask(command, false);
            } else {
                appendToList(command);
            }
        }
    }
    public static void main(String[] args) {
        Yapper yapper = new Yapper();
    }

}
