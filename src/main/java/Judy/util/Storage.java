package Judy.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Judy.task.Deadline;
import Judy.task.Event;
import Judy.task.Task;
import Judy.task.Todo;

/**
 * Handles file storage for saving and loading tasks in the Judy chatbot.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to a file specified by {@code filePath}.
     *
     * @param tasks the list of tasks to be saved to the file.
     */
    public void saveTasks(List<Task> tasks) {
        try {
            File file = new File(this.filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file specified by {@code filePath}.
     *
     * @return a list of {@code Task} objects loaded from the file. Returns an empty list if the file does not exist or is empty.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println("Warning: Skipping corrupted line -> " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Parses a line from the task file and converts it into a {@code Task} object.
     *
     * @param line the string line from the task file to be parsed.
     * @return a {@code Task} object corresponding to the parsed data.
     * @throws Exception if the line format is invalid or the task type is unknown.
     */
    private static Task parseTask(String line) throws Exception {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3 || parts.length > 4) {
            throw new Exception("Invalid format");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            String[] eventTimes = parts[3].split(" - ");
            task = new Event(description, eventTimes[0], eventTimes[1]);
            break;
        default:
            throw new Exception("Unknown task type");
        }
        task.setStatus(isDone);
        return task;
    }
}
