package alice.command;

import alice.Storage;
import alice.TaskList;
import alice.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHorizontalLine();
        tasks.getList();
        ui.printHorizontalLine();
    }
}
