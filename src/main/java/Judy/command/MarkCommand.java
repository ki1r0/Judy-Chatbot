package Judy.command;
import Judy.task.TaskList;
import Judy.ui.Ui;
import Judy.util.JudyException;
import Judy.util.Storage;

/**
 * Represents a command to mark or unmark a task in the task list.
 * This command updates the completion status of a specified task.
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isMark;

    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        return tasks.setMark(this.index, this.isMark);
    }
}
