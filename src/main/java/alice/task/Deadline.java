package alice.task;

import alice.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by.trim(), INPUT_FORMAT);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by.trim(), INPUT_FORMAT);
    }

    public LocalDateTime getByDate() {
        return this.by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + by.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

}
