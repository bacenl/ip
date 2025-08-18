/**
 * The Yapper class is the Chatbot for CS2103T.
 */
public class Yapper {
    public static void main(String[] args) {
        printGreet();
        printExit();
    }

    private static void printGreet() {
        System.out.println("Hello! I'm Yapper");
        System.out.println("What can I do for you?");
    }

    private static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
