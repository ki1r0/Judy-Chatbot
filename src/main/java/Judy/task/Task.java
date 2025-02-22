package Judy.task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with a given description.
     * The task is initially not completed.
     *
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() { return description; }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void setStatus(boolean status) {
        isDone = status;
    }



    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public String toDataString() {
        return (isDone ? "1 | " : "0 | ") + description;
    }
}
