import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    public String parseDateTime(String dateTime) {
        DateTimeFormatter formatters[] = {
                DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), // Format 2: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };
        for (DateTimeFormatter formatter : formatters) {
            //dateTime = dateTime.trim();
            try {
                if (formatter.toString().contains("HHmm")) {
                    return LocalDateTime.parse(dateTime, formatter).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                } else {
                    LocalDate date = LocalDate.parse(dateTime, formatter);
                    return date.atStartOfDay().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                }
            } catch (DateTimeParseException e) {}
        }
        System.out.println("Invalid date format.");
        return null;
    }

    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public String toDataString() {
        return (isDone ? "1 | " : "0 | ") + description;
    }
}
