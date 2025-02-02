import java.util.ArrayList;
import java.util.Arrays;

public class Judy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Judy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks(), storage);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (true) {
            try {
                String input = ui.readInput();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (JudyException e) {
                ui.showError(e.getMessage());
            }
            storage.saveTasks(tasks.getTasks());
        }
    }

    public static void main(String[] args) {
        new Judy("./data/judy.txt").run();
    }
}


