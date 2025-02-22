package Judy.task;

/**
 * Represents a task with a description only.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDataString() {
        return "T | " + super.toDataString();
    }
}
