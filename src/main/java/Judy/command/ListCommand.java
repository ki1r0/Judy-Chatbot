package Judy.command;
import Judy.task.TaskList;
import Judy.ui.Ui;
import Judy.util.JudyException;
import Judy.util.Storage;

/**
 * Represents a command to list all tasks in the task list.
 * This command retrieves and displays all tasks currently stored in the {@code TaskList}.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        return tasks.printList();
    }
}
