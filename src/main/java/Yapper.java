import commons.exceptions.InvalidCommandException;
import logic.commands.Command;
import logic.commands.ExitCommand;
import logic.parser.Parser;
import models.TaskList;
import storage.FileManager;
import ui.Ui;

/**
 * The Yapper class is the Chatbot for CS2103T.
 */
public class Yapper {
    private Ui ui;
    private TaskList tasks;
    private boolean isRunning;

    /**
     * Constructs a new Yapper chatbot instance.
     * Initializes the UI, loads tasks from storage, and starts the main loop.
     */
    public Yapper() {
        this.ui = new Ui();
        this.tasks = new TaskList(FileManager.loadTasks());
        this.isRunning = true;

        ui.printGreet();
        run();
    }

    /**
     * Main execution loop that processes user commands until exit
     */
    private void run() {
        while (isRunning) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parseCommand(userInput);

                if (command instanceof ExitCommand) {
                    isRunning = false;
                }
                command.execute(tasks, ui);

            } catch (InvalidCommandException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Main entry point for the Yapper chatbot application
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new Yapper();
    }
}
