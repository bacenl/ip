package commons.exceptions;
/**
 * Represents an exception due to an invalid command
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String e) {
        super(e);
    }
}
