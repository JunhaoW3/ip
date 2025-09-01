import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;

public class Alice {
    private static Storage storage;
    private static TaskList tasks;
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

    // Greeting the chat
    public static void greet() {
        horizontalLine();
        System.out.println("Hello! I'm " + getBotName());
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    // Exiting the chat
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public static void main(String[] args) throws AliceException {
        Scanner scanner = new Scanner(System.in);// Initialise scanner
        String filePath = Path.of("data", "alice.txt").toString();
        storage = new Storage(filePath);
        tasks = new TaskList();
        tasks = storage.load();

        // initial greeting from AliceBot
        greet();

        //Using lower case so that it accepts different iterations of "bye"
        String text = scanner.nextLine();
        String lowerCase = text.toLowerCase();

        // Continues running if input is not "bye"
        while (!lowerCase.equals("bye")) {
            try {
                // return list of texts when user input "list"
                if (lowerCase.equals("list")) {

                    horizontalLine();
                    tasks.getList();
                    horizontalLine();

                } else if (lowerCase.startsWith("mark") || lowerCase.startsWith("unmark")) {
                    int taskNumber = tasks.getTaskNumber(lowerCase);
                    Task task = tasks.get(taskNumber);

                    horizontalLine();
                    // check if it is to unmark or mark
                    if (lowerCase.contains("unmark")) {
                        task.markUndone();
                    } else {
                        task.markDone();
                    }
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
                            String by = String.format("%s/%s/%s", arr[1].substring(3), arr[2], arr[3].trim());
                            task = new Deadline(description, by);
                        } else {
                            if (arr.length < 3 || !arr[1].startsWith("from ") || !arr[4].startsWith("to ")) {
                                throw new AliceException("Event format should be: event <description> /from <start> to <end>");
                            }
                            String start = String.format("%s/%s/%s", arr[1].substring(5), arr[2], arr[3].trim());
                            String end = String.format("%s/%s/%s", arr[4].substring(3), arr[5], arr[6].trim());
                            //String at = start + "-" + end;
                            task = new Event(description, start, end);
                        }
                    }

                    tasks.add(task);

                    horizontalLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    String numberOfTasks = String.format("Now you have %d tasks in the list", tasks.getSize());
                    System.out.println(numberOfTasks);
                    horizontalLine();

                } else if (lowerCase.startsWith("delete")) {
                    horizontalLine();
                    tasks.deleteTask(text);
                    horizontalLine();
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

        storage.save(tasks);
        horizontalLine();
        exit();
    }
}
