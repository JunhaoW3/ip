import java.util.Scanner;
import java.util.ArrayList;

public class Alice {
    public static String botName = "Alice";
    public static ArrayList<Task> tasks = new ArrayList<>();

    // Getter for name
    public static String getBotName() {
        return botName;
    }

    // Printing horizontal lines
    public static void horizontalLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    // Exiting the chat
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public static int getTaskNumber(String text) throws AliceException {
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

    public static void deleteTask(String text) throws AliceException {
        int taskNumber = getTaskNumber(text);
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);

        horizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have 4 tasks in the list.");
        horizontalLine();
    }

    public static void main(String[] args) throws AliceException {
        Scanner scanner = new Scanner(System.in); // Initialise scanner
        //ArrayList<Task> tasks = new ArrayList<>(); // New ArrayList to store texts

        horizontalLine();
        System.out.println("Hello! I'm " + getBotName());
        System.out.println("What can I do for you?");
        horizontalLine();

        //Using lower case so that it accepts different iterations of "bye"
        String text = scanner.nextLine();
        String lowerCase = text.toLowerCase();

        // Continues running if input is not "bye"
        while (!lowerCase.equals("bye")) {
            try {
                // return list of texts when user input "list"
                if (lowerCase.equals("list")) {

                    horizontalLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        String item = String.format("%d. %s", i + 1, tasks.get(i).toString());
                        System.out.println(item);
                    }
                    horizontalLine();

                } else if (lowerCase.startsWith("mark") || lowerCase.startsWith("unmark")) {
                    String result;
                    int taskNumber = getTaskNumber(lowerCase);
                    Task task = tasks.get(taskNumber);

                    String description = tasks.get(taskNumber).getDescription().stripTrailing();

                    horizontalLine();
                    // check if it is to unmark or mark
                    if (lowerCase.contains("unmark")) {
                        task.markUndone();
                        result = String.format("[ ] %s", description);
                        System.out.println("OK, I've marked this task as not done yet:");
                    } else {
                        task.markDone();
                        result = String.format("[X] %s", description);
                        System.out.println("Nice! I've marked this task as done:");
                    }
                    System.out.println(result);
                    horizontalLine();

                } else if (lowerCase.startsWith("todo") || lowerCase.startsWith("deadline") || lowerCase.startsWith("event")) {
                    // Printing the tasks
                    Task task = new Task(text);
                    if (lowerCase.startsWith("todo")) {
                        if (text.trim().equalsIgnoreCase("todo")) {
                            throw new EmptyDescriptionException("todo");
                        }
                        task = new Todo(text);
                    } else if (lowerCase.startsWith("deadline") || lowerCase.startsWith("event")) {
                        String[] arr = text.split("/");
                        String description = arr[0];
                        if (lowerCase.startsWith("deadline")) {
                            if (arr.length < 2 || !arr[1].startsWith("by ")) {
                                throw new AliceException("Deadline format should be: deadline <description> /by <time>");
                            }
                            // substring to get rid of "by" in text
                            String by = arr[1].substring(3);
                            task = new Deadline(description, by);
                        } else {
                            if (arr.length < 3 || !arr[1].startsWith("from ") || !arr[2].startsWith("to ")) {
                                throw new AliceException("Event format should be: event <description> /from <start> to <end>");
                            }
                            // substring to get rid of "from" and "to" in text
                            String start = arr[1].substring(5);
                            String end = arr[2].substring(3);
                            task = new Event(description, start, end);
                        }
                    }

                    tasks.add(task);

                    horizontalLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    String numberOfTasks = String.format("Now you have %d tasks in the list", tasks.size());
                    System.out.println(numberOfTasks);
                    horizontalLine();

                } else if (lowerCase.startsWith("delete")) {
                    deleteTask(text);
                } else {
                    throw new UnknownCommandException();
                }

            } catch (AliceException e) {
                horizontalLine();
                System.out.println(e.getMessage());
                horizontalLine();
            }

            // Take in the next text
            text = scanner.nextLine();
            lowerCase = text.toLowerCase();
        }

        horizontalLine();
        exit();
    }
}
