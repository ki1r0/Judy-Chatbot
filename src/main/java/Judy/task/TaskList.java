package Judy.task;

import java.util.ArrayList;
import java.util.List;

import Judy.ui.Judy;
import Judy.util.JudyException;
import Judy.util.Storage;
import Judy.util.Util;

/**
 * Represents a list of tasks with storage functionality.
 */
public class TaskList {
    private final ArrayList<Task> list;
    private final Storage storage;

    /**
     * Constructs a TaskList with an initial list of tasks and a storage handler.
     *
     * @param list    The initial list of tasks.
     * @param storage The storage system for saving tasks.
     */
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
    public String deleteTask(int index) throws JudyException {
        if (list.isEmpty()) {
            return "The list is currently empty.";
        }
        if (index < 1 || index > list.size()) {
            throw new JudyException("Invalid task number. Please provide a valid task number.");
        }

        Task task = list.get(index - 1);
        list.remove(index - 1);
        storage.saveTasks(list);
        String response = String.format("Noted. I've removed this task:\n"
                + "      %s\n"
                + "    Now you have %d tasks in the list.", task, list.size());
        Util.printResponse(response);
        return (response);
    }

    /**
     * Parses the input message.
     *
     * @param index    the 1-based index of the task to be updated from the task list.
     * @param isMark   whether the input message is a mark or an unmark command.
     */
    public String setMark(int index, boolean isMark) throws JudyException {
        if (list.isEmpty()) {
            return "The list is currently empty.";
        }
        if (index < 1 || index > list.size()) {
            throw new JudyException("Invalid task number. Please provide a valid task number.");
        }
        Task task = list.get(index - 1);
        task.setStatus(isMark);
        storage.saveTasks(list);
        String message = isMark ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        Util.printResponse(message + "\n" + "    " + task);
        return message + "\n" + "    " + task;
    }

    /**
     * Adds a new task to the task list based on the provided task type and details.
     *
     * @param type        the type of task to add (TODO, DEADLINE, or EVENT).
     * @param details    details of the task added.
     */
    @SuppressWarnings("checkstyle:NeedBraces")
    public String addTask(TaskType type, String... details) throws JudyException {
        if (details[0].trim().isEmpty()) {
            throw new JudyException("The description cannot be empty. Please provide a valid description.");
        }

        Task task = null;
        switch (type) {
        case TODO:
            task = new Todo(details[0]);
            break;
        case DEADLINE:
            if (details.length < 2) throw new JudyException("Deadline task requires a description and a deadline.");
            task = new Deadline(details[0], details[1]);
            break;
        case EVENT:
            if (details.length < 3) throw new JudyException("Event task requires a description, start, and end time.");
            task = new Event(details[0], details[2], details[3]);
            break;
        default:
            throw new JudyException("Unknown task type. Please try again.");
        }

        list.add(task);
        storage.saveTasks(list);
        String response = String.format("Got it. I've added this task:\n"
                + "      %s\n"
                + "    Now you have %d tasks in the list.", task, list.size());
        Util.printResponse(response);
        return response;
    }
    /**
     * Finds all tasks in the task list.
     *
     * @param keyword         the keyword used to find the tasks containing it.
     */
    public String findTask(String keyword) {
        if (list.isEmpty()) {
            return "The list is currently empty.";
        }
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        for (Task task : this.list) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                response.append("    ").append(index).append(". ").append(task.toString()).append("\n");
                index++;
            }
        }
        if (this.list.isEmpty() || index == 1) {
            response.append("    No tasks containing the keyword found in the list.");
        }
        Util.printResponse(response.toString());
        return response.toString();
    }

    /**
     * Prints all tasks in the task list with their corresponding index numbers.
     */
    public String printList() {
        if (list.isEmpty()) {
            return "The list is currently empty.";
        }
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
        return response.toString();
    }
}
