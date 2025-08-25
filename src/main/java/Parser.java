import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     * Parses the user command string and returns a corresponding Command object
     *
     * @param command the raw user input command
     * @return a Command object representing the parsed command
     * @throws InvalidCommandException if the command format is invalid or parameters are missing
     */
    public static Command parseCommand(String command) throws InvalidCommandException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("delete ")) {
            return parseDeleteCommand(command);
        } else if (command.startsWith("mark ")) {
            return parseMarkCommand(command, true);
        } else if (command.startsWith("unmark ")) {
            return parseMarkCommand(command, false);
        } else if (command.startsWith("todo ")) {
            return parseTodoCommand(command);
        } else if (command.startsWith("deadline ")) {
            return parseDeadlineCommand(command);
        } else if (command.startsWith("event ")) {
            return parseEventCommand(command);
        } else {
            throw new InvalidCommandException("Sorry, please enter a valid command"
                    + "(mark / unmark / todo / deadline / event / list / delete / bye)");
        }
    }

    /**
     * Parses a delete command and extracts the task index
     *
     * @param command the delete command string
     * @return a DeleteCommand with the task index
     * @throws InvalidCommandException if the command format is invalid
     */
    private static Command parseDeleteCommand(String command) throws InvalidCommandException {
        Pattern pattern = Pattern.compile("^delete (\\d+)$");
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1)) - 1;
            return new DeleteCommand(index);
        } else {
            throw new InvalidCommandException("Please ensure the following format 'delete (positive integer)'");
        }
    }

    /**
     * Parses a mark/unmark command and extracts the task index and mark status
     *
     * @param command the mark/unmark command string
     * @param isDone true for mark command, false for unmark command
     * @return a MarkCommand with the task index and mark status
     * @throws InvalidCommandException if the command format is invalid
     */
    private static Command parseMarkCommand(String command, boolean isDone) throws InvalidCommandException {
        Pattern pattern = Pattern.compile("^(mark|unmark) (\\d+)$");
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(2)) - 1;
            return new MarkCommand(index, isDone);
        } else {
            throw new InvalidCommandException("Please ensure the following format 'mark / unmark (positive integer)'");
        }
    }

    /**
     * Parses a todo command and extracts the task description
     *
     * @param command the todo command string
     * @return a ToDoCommand with the task description
     * @throws InvalidCommandException if the description is empty
     */
    private static Command parseTodoCommand(String command) throws InvalidCommandException {
        String name = command.substring(5).trim();
        if (name.isEmpty()) {
            throw new InvalidCommandException("Description of ToDo cannot be empty");
        }
        return new ToDoCommand(name);
    }

    /**
     * Parses a deadline command and extracts the task description and due date
     *
     * @param command the deadline command string
     * @return a DeadlineCommand with description and due date
     * @throws InvalidCommandException if parameters are missing or invalid
     */
    private static Command parseDeadlineCommand(String command) throws InvalidCommandException {
        String[] parts = command.substring(9).trim().split("/by", 2);
        if (parts.length != 2) {
            throw new InvalidCommandException("Need /by parameter");
        }

        String name = parts[0].trim();
        String rawBy = parts[1].trim();

        if (name.isEmpty()) {
            throw new InvalidCommandException("Description of Deadline cannot be empty");
        }
        if (rawBy.isEmpty()) {
            throw new InvalidCommandException("/by of Deadline cannot be empty");
        }

        try {
            LocalDate by = LocalDate.parse(rawBy);
            return new DeadlineCommand(name, by);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format");
        }
    }

    /**
     * Parses an event command and extracts the task description, start date, and end date
     *
     * @param command the event command string
     * @return an EventCommand with description and date range
     * @throws InvalidCommandException if parameters are missing or invalid
     */
    private static Command parseEventCommand(String command) throws InvalidCommandException {
        String[] parts = command.substring(6).trim().split("/from|/to");
        if (parts.length != 3) {
            throw new InvalidCommandException("Need both /from and /to parameters");
        }

        boolean isFromFirst = command.contains("/from") && (command.indexOf("/from") < command.indexOf("/to"));
        String description = parts[0].trim();
        String rawFrom = isFromFirst ? parts[1].trim() : parts[2].trim();
        String rawTo = isFromFirst ? parts[2].trim() : parts[1].trim();

        if (description.isEmpty()) {
            throw new InvalidCommandException("Description of Event cannot be empty");
        }
        if (rawFrom.isEmpty() || rawTo.isEmpty()) {
            throw new InvalidCommandException("/from or /to of Event cannot be empty");
        }

        try {
            LocalDate from = LocalDate.parse(rawFrom);
            LocalDate to = LocalDate.parse(rawTo);
            return new EventCommand(description, from, to);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format");
        }
    }
}
