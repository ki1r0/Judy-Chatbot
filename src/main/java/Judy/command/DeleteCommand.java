package Judy.command;
import Judy.task.TaskList;
import Judy.ui.Ui;
import Judy.util.JudyException;
import Judy.util.Storage;

/**
 * Represents a command to delete a task from the task list.
 * This command removes a task at a specified index from the {@code TaskList}.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        return tasks.deleteTask(index);
    }
}
