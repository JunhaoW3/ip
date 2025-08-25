import java.util.Scanner;
import java.util.ArrayList;

public class Alice {
    public static String botName = "Alice";

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Initialise scanner
        ArrayList<Task> tasks = new ArrayList<>(); // New ArrayList to store texts

        horizontalLine();
        System.out.println("Hello! I'm " + getBotName());
        System.out.println("What can I do for you?");
        horizontalLine();

        //Using lower case so that it accepts different iterations of "bye"
        String text = scanner.nextLine();
        String lowerCase = text.toLowerCase();

        // Continues running if input is not "bye"
        while (!lowerCase.equals("bye")) {

            // return list of texts when user input "list"
            if (lowerCase.equals("list")) {

                horizontalLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    String item = String.format("%d. %s", i + 1, tasks.get(i).toString());
                    System.out.println(item);
                }
                horizontalLine();

            } else if (lowerCase.contains("mark") || lowerCase.contains("unmark")) {
                String result;
                String[] arr = lowerCase.split(" ");
                int taskNumber = Integer.parseInt(arr[1]) - 1;
                String description = tasks.get(taskNumber).getDescription();

                horizontalLine();
                // check if it is to unmark or mark
                if (lowerCase.contains("unmark")) {
                    tasks.get(taskNumber).markUndone();
                    result = String.format("[ ] %s", description);
                    System.out.println("OK, I've marked this task as not done yet:");
                } else {
                    tasks.get(taskNumber).markDone();
                    result = String.format("[X] %s", description);
                    System.out.println("Nice! I've marked this task as done:");
                }
                System.out.println(result);
                horizontalLine();

            } else {
                Task task = new Task(text);
                if (lowerCase.contains("todo")) {
                    task = new Todo(text);
                } else if (lowerCase.contains("deadline") || lowerCase.contains("event")) {
                    String[] arr = text.split("/");
                    String description = arr[0];
                    if (lowerCase.contains("deadline")) {
                        // substring to get rid of "by" in text
                        String by = arr[1].substring(3);
                        task = new Deadline(description, by);
                    } else {
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

            }

            // Take in the next text
            text = scanner.nextLine();
            lowerCase = text.toLowerCase();
        }

        horizontalLine();
        exit();
    }
}
