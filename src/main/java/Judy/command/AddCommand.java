package Judy.command;

import Judy.task.TaskList;
import Judy.task.TaskType;
import Judy.ui.Ui;
import Judy.util.JudyException;
import Judy.util.Storage;

/**
 * Represents a command to add a task to the task list.
 * This command is responsible for creating a task of a specific type with given details
 * and adding it to the {@code TaskList}.
 */
public class AddCommand extends Command {
    private final TaskType type;
    private final String[] details;

    /**
     * Constructs an {@code AddCommand} with the specified task type and details.
     *
     * @param type    the type of task to be added
     * @param details the details of the task (e.g., description, deadline, event time)
     */
    public AddCommand(TaskType type, String... details) {
        this.type = type;
        this.details = details;
    }

    /**
     * Executes the command to add a task to the TaskList.
     *
     * @param tasks    the {@code TaskList} to which the task will be added
     * @param ui       the {@code Ui} instance for user interaction (not directly used here)
     * @param storage  the {@code Storage} instance for persisting tasks
     * @throws JudyException if there's an error during task creation
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        return tasks.addTask(type, details);
    }
}
