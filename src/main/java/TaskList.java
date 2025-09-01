import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public void getList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String item = String.format("%d. %s", i + 1, tasks.get(i).toString());
            System.out.println(item);
        }
    }

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

    public void printAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        String numberOfTasks = String.format("Now you have %d tasks in the list", tasks.size());
        System.out.println(numberOfTasks);
    }

    public void deleteTask(String text) throws AliceException {
        int taskNumber = getTaskNumber(text);
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        String tasksLeft = String.format("Now you have %s tasks in the list.", tasks.size());
        System.out.println(tasksLeft);
    }
}
