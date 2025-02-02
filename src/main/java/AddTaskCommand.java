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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        tasks.addTask(description, type, deadline, start, end);
    }
}
