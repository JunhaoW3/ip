package alice;

import alice.command.*;
import alice.exceptions.AliceException;
import alice.exceptions.EmptyDescriptionException;
import alice.exceptions.UnknownCommandException;

/**
 * Represents an object that parses the commands of users,
 * and returns an appropriate command
 */
public class Parser {

    /**
     * Returns an appropriate command using the user's input
     *
     * @param fullCommand full input of the user
     * @return Command
     * @throws AliceException If an unknown command is given
     */
    public static Command parse(String fullCommand) throws AliceException {
        String lower = fullCommand.trim().toLowerCase();

        if (lower.equals("list")) {
            return new ListCommand();
        } else if (lower.startsWith("mark")) {
            return new MarkCommand(fullCommand, true);
        } else if (lower.startsWith("unmark")) {
            return new MarkCommand(fullCommand, false);
        } else if (lower.startsWith("todo")) {
            if (fullCommand.trim().equalsIgnoreCase("todo")) {
                throw new EmptyDescriptionException("todo");
            }
            return new AddTodoCommand(fullCommand);
        } else if (lower.startsWith("deadline")) {
            return new AddDeadlineCommand(fullCommand);
        } else if (lower.startsWith("event")) {
            return new AddEventCommand(fullCommand);
        } else if (lower.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (lower.startsWith("find")) {
            return new FindCommand(fullCommand);
        } else if (lower.startsWith("edit")) {
            return new EditCommand(fullCommand);
        } else if (lower.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new UnknownCommandException();
        }
    }
}
