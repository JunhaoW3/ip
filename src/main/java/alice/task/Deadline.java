package alice.task;

import alice.Task;
import alice.exceptions.AliceException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends DateTask {

    private LocalDateTime by;

    public Deadline(String description, String by) throws AliceException {
        super(description);
        this.by = parseDate(by);
    }

    public Deadline(String description, boolean isDone, String by) throws AliceException {
        super(description, isDone);
        this.by = parseDate(by);
    }

    public void setBy(String by) throws AliceException {
        this.by = parseDate(by);
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
