public class AddTodoCommand extends Command {
    private final String input;

    public AddTodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AliceException {
        Task todo = new Todo(input);

        ui.horizontalLine();
        tasks.add(todo);
        ui.horizontalLine();

        storage.save(tasks);
    }
}
