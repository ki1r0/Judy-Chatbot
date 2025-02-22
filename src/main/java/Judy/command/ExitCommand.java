package Judy.command;
import Judy.task.TaskList;
import Judy.ui.Ui;
import Judy.util.JudyException;
import Judy.util.Storage;

/**
 * Represents a command to terminate the application.
 * This command signals the end of user interaction and application execution.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        return ui.showEnd();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
