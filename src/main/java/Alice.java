import java.util.Scanner;
import java.nio.file.Path;

public class Alice {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Alice(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);// Initialise scanner

        // initial greeting from AliceBot
        ui.greet();

        //Using lower case so that it accepts different iterations of "bye"
        String text = scanner.nextLine();
        String lowerCase = text.toLowerCase();

        // Continues running if input is not "bye"
        while (!lowerCase.equals("bye")) {
            try {
                // return list of texts when user input "list"
                if (lowerCase.equals("list")) {

                    ui.horizontalLine();
                    tasks.getList();
                    ui.horizontalLine();

                } else if (lowerCase.startsWith("mark") || lowerCase.startsWith("unmark")) {
                    int taskNumber = tasks.getTaskNumber(lowerCase);
                    Task task = tasks.get(taskNumber);

                    ui.horizontalLine();
                    // check if it is to unmark or mark
                    if (lowerCase.contains("unmark")) {
                        task.markUndone();
                    } else {
                        task.markDone();
                    }
                    ui.horizontalLine();

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

                    ui.horizontalLine();
                    tasks.add(task);
                    ui.horizontalLine();

                } else if (lowerCase.startsWith("delete")) {
                    ui.horizontalLine();
                    tasks.deleteTask(text);
                    ui.horizontalLine();
                } else {
                    throw new UnknownCommandException();
                }

            } catch (AliceException e) {
                ui.horizontalLine();
                System.out.println(e.getMessage());
                ui.horizontalLine();
            }

            // Take in the next text
            text = scanner.nextLine();
            lowerCase = text.toLowerCase();
        }

        storage.save(tasks);
        ui.horizontalLine();
        ui.exit();
    }

    public static void main(String[] args) {
        String filePath = Path.of("data", "alice.txt").toString();
        new Alice(filePath).run();
    }
}
