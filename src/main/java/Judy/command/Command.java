package Judy.command;
import Judy.task.*;
import Judy.ui.*;
import Judy.util.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JudyException;
    public boolean isExit() {
        return false;
    }
}
