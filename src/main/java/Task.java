import javax.swing.*;

public class Task {
    private final String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void setStatus(boolean status) {
        isDone = status;
    }

    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
