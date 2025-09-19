package alice.command;

import alice.Storage;
import alice.Task;
import alice.TaskList;
import alice.Ui;
import alice.exceptions.AliceException;
import alice.task.Deadline;
import alice.task.Event;
import alice.task.Todo;

public class EditCommand extends Command {
    private final String input;

    public EditCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AliceException {
        try {
            String[] split = input.trim().split(" ", 3);
            if (split.length < 3) {
                throw new AliceException("Edit format: edit <task_number> <new details>");
            }

            int taskIndex = Integer.parseInt(split[1]) - 1;
            Task task = tasks.getTask(taskIndex);

            String details = split[2].trim();

            if (task instanceof Todo) {
                task.setDescription(details);

            } else if (task instanceof Deadline) {
                // Expected format: description /by <datetime>
                String[] parts = details.split(" /by ", 2);
                if (parts.length < 2) {
                    throw new AliceException("Deadline edit format: edit <num> <desc> /by <d/M/yyyy HHmm>");
                }
                task.setDescription(parts[0].trim());
                ((Deadline) task).setBy(parts[1].trim());

            } else if (task instanceof Event) {
                // Expected format: description /from <start> /to <end>
                String[] parts = details.split(" /from | /to ", 3);
                if (parts.length < 3) {
                    throw new AliceException("Event edit format: edit <num> <desc> /from <start> /to <end>");
                }
                task.setDescription(parts[0].trim());
                ((Event) task).setStart(parts[1].trim());
                ((Event) task).setEnd(parts[2].trim());
            }

            storage.save(tasks);

            return "Got it. I've updated this task:\n  " + task;

        } catch (IndexOutOfBoundsException e) {
            throw new AliceException("Task number not found.");
        } catch (NumberFormatException e) {
            throw new AliceException("Invalid task number.");
        }
    }

}
