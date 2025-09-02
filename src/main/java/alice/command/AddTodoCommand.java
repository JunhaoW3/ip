package alice.command;

import alice.Storage;
import alice.Task;
import alice.TaskList;
import alice.Ui;
import alice.exceptions.AliceException;
import alice.task.Todo;

public class AddTodoCommand extends Command {
    private final String input;

    public AddTodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AliceException {
        Task todo = new Todo(input);

        ui.printHorizontalLine();
        tasks.addTask(todo);
        tasks.printAdd(todo);
        ui.printHorizontalLine();

        storage.save(tasks);
    }
}
