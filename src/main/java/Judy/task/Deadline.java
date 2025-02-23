package Judy.task;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructs a Deadline task with a description and a deadline.
     *
     * @param description A brief description of the task.
     * @param deadline    The deadline of the task in string format.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + deadline;
    }
}
