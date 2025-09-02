package alice;

/**
 * Represents the tasks that the user can add to list
 */
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

    /**
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @return the status of the task, whether it is completed or not. "X" for completed, " " for not completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
        String description = this.getDescription().stripTrailing();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("[X] %s", description));
    }

    /**
     * Marks the task as not done
     */
    public void markUndone() {
        this.isDone = false;
        String description = this.getDescription().stripTrailing();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format("[ ] %s", description));
    }

    /**
     *
     * @return String in the format to be written to the file
     */
    public String toFileFormat() {
        return (isDone ? "X" : " ") + " | " + description;
    }

    /**
     *
     * @return String format for the task
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), this.getDescription());
    }
}
