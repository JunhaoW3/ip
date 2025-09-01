import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start.trim(), INPUT_FORMAT);
        this.end = LocalDateTime.parse(end.trim(), INPUT_FORMAT);
    }

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = LocalDateTime.parse(start.trim(), INPUT_FORMAT);
        this.end = LocalDateTime.parse(end.trim(), INPUT_FORMAT);
    }

    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " + start.format(INPUT_FORMAT) + " | " + end.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + start.format(OUTPUT_FORMAT) + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }
}
