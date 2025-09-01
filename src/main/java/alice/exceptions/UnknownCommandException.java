package alice.exceptions;

public class UnknownCommandException extends AliceException {

    public UnknownCommandException() {
        super("Sorry, I don't know what that means. Please enter a task: alice.task.Todo, alice.task.Deadline, alice.task.Event, and add a description");
    }

}
