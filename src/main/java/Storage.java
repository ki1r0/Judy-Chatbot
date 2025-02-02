import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        if (!file.exists()) {
            return tasks; // Return an empty list if the file doesn't exist
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
