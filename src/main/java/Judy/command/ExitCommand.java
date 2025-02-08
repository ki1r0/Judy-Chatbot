package Judy.command;
import Judy.task.*;
import Judy.ui.*;
import Judy.util.*;

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
