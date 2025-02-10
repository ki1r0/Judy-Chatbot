package Judy.command;
import Judy.task.TaskType;
import Judy.task.TaskList;
import Judy.ui.Ui;
import Judy.util.Storage;
import Judy.util.JudyException;
public class AddCommand extends Command {
    private final TaskType type;
    private final String[] details;

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
