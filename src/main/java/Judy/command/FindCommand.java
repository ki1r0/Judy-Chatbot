package Judy.command;
import Judy.task.*;
import Judy.ui.*;
import Judy.util.*;

public class FindCommand extends Command{

    private final String keyward;

    public FindCommand(String keyward) {
        this.keyward = keyward;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JudyException {
        tasks.findTask(keyward);
    }
}
