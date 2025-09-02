package alice;

import alice.task.Deadline;
import alice.task.Event;
import alice.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String FILEPATH;

    public Storage(String filePath) {
        this.FILEPATH = filePath;
    }

    // Load tasks from file, returns empty list if file does not exist
    public TaskList load() {
        TaskList tasks = new TaskList();
        File file = new File(FILEPATH);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    tasks.addTask(parseTask(line));
                } catch (Exception e) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    // Saves tasks to file
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (Task task : tasks.getAllTasks()) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("X");
        String description = parts[2];

        switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                return new Deadline(description, isDone, parts[3]);
            case "E":
                return new Event(description, isDone, parts[3], parts[4]);
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }
}
