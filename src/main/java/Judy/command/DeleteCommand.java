package Judy.command;
import Judy.task.*;
import Judy.ui.*;
import Judy.util.*;
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        tasks.deleteTask(index);
    }
}
