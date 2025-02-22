package Judy.command;
import Judy.task.TaskList;
import Judy.ui.Ui;
import Judy.util.JudyException;
import Judy.util.Storage;

/**
 * Represents an abstract command that can be executed within the task management system.
 * Each concrete command must implement the {@code execute} method to define its behavior.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws JudyException;
    public boolean isExit() {
        return false;
    }
}
