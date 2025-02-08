package Judy.command;
import Judy.task.*;
import Judy.ui.*;
import Judy.util.*;

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
