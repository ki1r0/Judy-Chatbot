package Judy.task;
import Judy.task.*;
import Judy.ui.*;
import Judy.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    private ArrayList<Task> list;
    private Storage storage;

    public TaskList(List<Task> list, Storage storage) {
        this.list = new ArrayList<>(list);
        this.storage = storage;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return this.list;
    }

    /**
     * Deletes a task from the {@code TaskList} at the specified index.
     *
     * @param index    the 1-based index of the task to be deleted from the task list.
     */
    public void deleteTask(int index) {
        if (index < 1 || index > list.size()) {
            Util.printError("Invalid task number. Please provide a number between 1 and " + list.size() + ".");
            return;
        }

        Task task = list.get(index - 1);
        list.remove(index - 1);
        storage.saveTasks(list);
        String response = String.format("Noted. I've removed this task:\n"
                + "      %s\n"
                + "    Now you have %d tasks in the list.", task, list.size());
        Util.printResponse(response);
    }

    /**
     * Parses the input message.
     *
     * @param index    the 1-based index of the task to be updated from the task list.
     * @param isMark   whether the input message is a mark or an unmark command.
     */
    public void updateStatus(int index, boolean isMark) {
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

    /**
     * Adds a new task to the task list based on the provided task type and details.
     *
     * @param description an array of strings representing the task description. Cannot be empty.
     * @param type        the type of task to add (TODO, DEADLINE, or EVENT).
     * @param deadline    the deadline for DEADLINE tasks. Can be {@code null} for other task types.
     * @param start       the start time for EVENT tasks. Can be {@code null} for other task types.
     * @param end         the end time for EVENT tasks. Can be {@code null} for other task types.
     */
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
        String response = String.format("Got it. I've added this task:\n"
                + "      %s\n"
                + "    Now you have %d tasks in the list.", task, list.size());
        Util.printResponse(response);
    }

    /**
     * Finds all tasks in the task list.
     *
     * @param keyward         the keyward used to find the tasks containing it.
     */
    public void findTask(String keyward) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        for (Task task : this.list) {
            if (task.getDescription().toLowerCase().contains(keyward)) {
                response.append("    ").append(index).append(". ").append(task.toString()).append("\n");
                index++;
            }
        }
        if (this.list.isEmpty() || index == 1) {
            response.append("    No tasks containing the keyward found in the list.");
        }
        Util.printResponse(response.toString());
    }

    /**
     * Prints all tasks in the task list with their corresponding index numbers.
     */
    public void printList() {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : this.list) {
            response.append("    ").append(index).append(". ").append(task.toString()).append("\n");
            index++;
        }

        if (this.list.isEmpty()) {
            response.append("    No tasks found in the list.");
        }
        Util.printResponse(response.toString());
    }
}
