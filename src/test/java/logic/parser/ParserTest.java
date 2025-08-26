package logic.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import commons.exceptions.InvalidCommandException;
import logic.commands.Command;
import logic.commands.DeleteCommand;
import logic.commands.ExitCommand;
import logic.commands.ListCommand;

class ParserTest {

    @Test
    void parseCommand_exitCommand_returnsExitCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parseCommand_listCommand_returnsListCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parseCommand_invalidCommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("invalid command"));
    }

    @Test
    void parseDeleteCommand_validInput_returnsDeleteCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parseDeleteCommand_invalidFormat_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("delete"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("delete abc"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("delete -1"));
    }

}
