package alice.command;

import alice.Storage;
import alice.Task;
import alice.TaskList;
import alice.Ui;
import alice.exceptions.AliceException;

public class MarkCommand extends Command {
    private final String input;
    private final boolean isMark;

    public MarkCommand(String input, boolean isMark) {
        this.input = input;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AliceException {
        int taskNumber = tasks.getTaskNumber(input);
        Task task = tasks.getTask(taskNumber);

        ui.printHorizontalLine();
        if (isMark) {
            task.markDone();
        } else {
            task.markUndone();
        }
        ui.printHorizontalLine();
    }
}
