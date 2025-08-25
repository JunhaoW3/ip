public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String doneBox;
        if (this.isDone) {
            doneBox = "[X]";
        } else {
            doneBox = "[ ]";
        }
        return String.format("%s %s", doneBox, this.getDescription());
    }
}
