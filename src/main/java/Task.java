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
        String description = this.getDescription().stripTrailing();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("[X] %s", description));
    }

    public void markUndone() {
        this.isDone = false;
        String description = this.getDescription().stripTrailing();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format("[ ] %s", description));
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
