import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> list;
    private Storage storage;

    public TaskList(Storage storage) {
        this.list = new ArrayList<>();
        this.storage = storage;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return this.list;
    }

    public void deleteTask(int index) {
        if (index < 1 || index > list.size()) {
            Util.printError("Invalid task number. Please provide a number between 1 and " + list.size() + ".");
            return;
        }

        Task task = list.get(index - 1);
        list.remove(index - 1);
        storage.saveTasks(list);
        String response = String.format("Noted. I've removed this task:\n" +
                "      %s\n" +
                "    Now you have %d tasks in the list.", task, list.size());
        Util.printResponse(response);
    }

    public void updateStatus (int index, boolean isMark) {
        if (index < 1 || index > list.size()) {
            Util.printError("Invalid task number. Please provide a number between 1 and " + list.size() + ".");
            return;
        }
        Task task = list.get(index - 1);
        task.setStatus(isMark);
        storage.saveTasks(list);
        String message = isMark ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        Util.printResponse(message + "\n" + "    " + task);
    }

    public void addTask(String[] description, TaskType type, String[] deadline, String[] start, String[] end) {
        if (description.length == 0) {
            Util.printError("The description cannot be empty. Please provide a valid description.");
            return;
        }

        Task task = null;
        switch (type) {
            case TODO:
                task = new Todo(String.join(" ", description));
                break;
            case DEADLINE:
                task = new Deadline(String.join(" ", description), String.join(" ", deadline));
                break;
            case EVENT:
                task = new Event(String.join(" ", description), String.join(" ", start), String.join(" ", end));
                break;
            default:
                Util.printError("Unknown task type. Please try again.");
                return;
        }

        list.add(task);
        storage.saveTasks(list);
        String response = String.format("Got it. I've added this task:\n" +
                "      %s\n" +
                "    Now you have %d tasks in the list.", task, list.size());
        Util.printResponse(response);
    }

    public void printList() {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : this.list) {
            response.append("    ").append(index).append(". ").append(task.toString());
            index++;
        }

        if (this.list.isEmpty()) {
            response.append("    No tasks found in the list.");
        }
        Util.printResponse(response.toString()); // Use printResponse for consistent formatting
    }
}
