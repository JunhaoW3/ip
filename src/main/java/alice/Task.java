package alice;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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

    public String toFileFormat() {
        return (isDone ? "X" : " ") + " | " + description;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), this.getDescription());
    }
}
