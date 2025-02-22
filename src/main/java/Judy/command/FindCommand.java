package Judy.command;

import Judy.task.TaskList;
import Judy.ui.Ui;
import Judy.util.JudyException;
import Judy.util.Storage;

/**
 * Represents a command to find tasks in the task list based on a keyword.
 * This command searches for tasks containing the specified keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyward) {
        this.keyword = keyward;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        return tasks.findTask(keyword);
    }
}
