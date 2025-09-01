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
        Task task = tasks.get(taskNumber);

        ui.horizontalLine();
        if (isMark) {
            task.markDone();
        } else {
            task.markUndone();
        }
        ui.horizontalLine();
    }
}
