package alice.command;

import alice.Storage;
import alice.Task;
import alice.TaskList;
import alice.Ui;
import alice.exceptions.AliceException;
import alice.task.Deadline;

public class AddDeadlineCommand extends Command {
    private final String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AliceException {
        String lowerCase = input.toLowerCase();
        String[] arr = input.split("/");
        String description = arr[0];

        if (arr.length < 2 || !arr[1].startsWith("by ")) {
            throw new AliceException("Deadline format should be: deadline <description> /by <time>");
        }
        String by = String.format("%s/%s/%s", arr[1].substring(3), arr[2], arr[3].trim());
        Task deadline = new Deadline(description, by);

        ui.printHorizontalLine();
        tasks.addTask(deadline);
        tasks.printAdd(deadline);
        ui.printHorizontalLine();

        storage.save(tasks);
    }
}
