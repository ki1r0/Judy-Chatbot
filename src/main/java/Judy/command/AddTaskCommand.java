package Judy.command;
import Judy.task.TaskType;
import Judy.task.TaskList;
import Judy.ui.Ui;
import Judy.util.Storage;
import Judy.util.JudyException;
public class AddTaskCommand extends Command {
    private final String[] description;
    private final TaskType type;
    private final String[] deadline, start, end;

    public AddTaskCommand(String[]description, TaskType type, String[] deadline, String[] start, String[] end) {
        this.description = description;
        this.type = type;
        this.deadline = deadline;
        this.start = start;
        this.end = end;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        tasks.addTask(description, type, deadline, start, end);
    }
}
