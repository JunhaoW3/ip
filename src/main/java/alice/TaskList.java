package alice;

import alice.exceptions.AliceException;
import alice.exceptions.InvalidTaskNumberException;

import java.util.ArrayList;

/**
 * Keeps track of the tasks in the list
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the list
     *
     * @param task Task to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task of a list from a particular index
     * If the index is out of bounds, an error will be thrown
     *
     * @param index Index of the task in the list
     * @return Task at the given index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     *
     * @return Size of the list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     *
     * @return list of all the tasks
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Prints out the tasks in the list
     */
    public String getList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     *
     * @param text Text for the task to be found
     * @return Index of the task in the list
     * @throws AliceException If task number is invalid (e.g. out of bounds)
     */
    public int getTaskNumber(String text) throws AliceException {
        String[] arr = text.split(" ");
        if (arr.length < 2) {
            throw new InvalidTaskNumberException();
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(arr[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }

        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }

        return taskNumber;
    }

    /**
     * Prints the output to show that the task is added to the list,
     * as well as the current number and list of tasks in the list
     *
     * @param task Task to be added to the list
     */
    public String printAdd(Task task) {
        return String.format("%s\nGot it. I've added this task:\n%s\nNow you have %d tasks in the list\n%s",
                Ui.printHorizontalLine(), task, tasks.size(), Ui.printHorizontalLine());
    }

    /**
     * Deletes the task with matching text string
     *
     * @param text Text of task to be deleted
     * @throws AliceException If there is no matching task
     */
    public String deleteTask(String text) throws AliceException {
        int taskNumber = getTaskNumber(text);
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);

        return String.format("%s\nNoted. I've removed this task:\n%s\nNow you have %s tasks in the list.\n%s",
                Ui.printHorizontalLine(), task, tasks.size(), Ui.printHorizontalLine());
    }

    /**
     *
     * @param text Text that you intend to find
     * @return List of tasks that matches the text
     */
    public String findTask(String text) {
        int count = 0;
        String[] words = text.split(" ");
        String keyword = words[1];
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s\n", Ui.printHorizontalLine()));
        sb.append("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = tasks.get(i).toString();
            if (taskString.contains(keyword)) {
                sb.append(String.format("%d. %s\n", count + 1, taskString));
                count++;
            }
        }
        sb.append(String.format("%s\n", Ui.printHorizontalLine()));
        return sb.toString();
    }
}
