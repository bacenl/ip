import java.util.Scanner;
/**
 * The Yapper class is the Chatbot for CS2103T.
 */
public class Yapper {
    private Scanner inputScanner;

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

    private void listenForCommand() {
        while (true) {
            String command = inputScanner.nextLine();

            if (command.equals("bye")) {
                printExit();
                break;
            } else {
                System.out.println(command);
            }
        }
    }
    public static void main(String[] args) {
        Yapper yapper = new Yapper();
    }

}
