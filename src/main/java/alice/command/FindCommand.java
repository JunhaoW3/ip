package alice.command;

import alice.Storage;
import alice.TaskList;
import alice.Ui;

public class FindCommand extends Command {
    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.horizontalLine();
        tasks.findTask(input);
        ui.horizontalLine();
    }

}
