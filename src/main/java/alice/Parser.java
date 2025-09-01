package alice;

import alice.command.*;
import alice.exceptions.AliceException;
import alice.exceptions.EmptyDescriptionException;
import alice.exceptions.UnknownCommandException;

public class Parser {

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
        } else if (lower.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new UnknownCommandException();
        }
    }
}
