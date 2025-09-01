package alice.command;

import alice.Storage;
import alice.TaskList;
import alice.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.horizontalLine();
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
