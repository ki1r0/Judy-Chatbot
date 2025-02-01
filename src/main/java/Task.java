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

    public String toDataString() {
        return (isDone ? "1 | " : "0 | ") + description;
    }
}
