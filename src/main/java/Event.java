public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStartTime() {
        return this.start;
    }

    public String getEndTime() {
        return this.end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.getStartTime(), this.getEndTime());
    }
}
