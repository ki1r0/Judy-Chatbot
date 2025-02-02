package Judy.command;
import Judy.task.*;
import Judy.ui.*;
import Judy.util.*;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        tasks.printList();
    }
}
