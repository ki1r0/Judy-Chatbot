package Judy.command;
import Judy.task.*;
import Judy.ui.*;
import Judy.util.*;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        ui.showEnd();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
