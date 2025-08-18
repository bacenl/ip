import java.util.ArrayList;
import java.util.Scanner;
/**
 * The Yapper class is the Chatbot for CS2103T.
 */
public class Yapper {
    private Scanner inputScanner;
    private ArrayList<String> tasks = new ArrayList<>();

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
        tasks.add(input);
        System.out.printf("added: %s\n", input);
    }

    private void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
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
            } else {
                appendToList(command);
            }
        }
    }
    public static void main(String[] args) {
        Yapper yapper = new Yapper();
    }

}
