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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        return tasks.deleteTask(index);
    }
}
