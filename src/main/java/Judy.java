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
        while (true) {
            try {
                String input = ui.readInput();
                if (input.equals("bye")) {
                    ui.showEnd();
                    break;
                }
                handleCommand(input);
            } catch (JudyException e) {
                ui.showError(e.getMessage());
            }
            storage.saveTasks(tasks.getTasks());
        }
    }

    public static void main(String[] args) {
        new Judy("./data/judy.txt").run();
    }

    private void handleCommand(String input) throws JudyException {

        if (input.equals("list")) {
            tasks.printList();
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            String[] parts = input.split(" ");
            if (parts.length > 1) {
                int number = Integer.parseInt(parts[1]);
                boolean isMark = input.startsWith("mark");
                tasks.updateStatus(number, isMark);
            } else {
                throw new JudyException("Invalid mark command. Usage: mark <task number>");
            }
        } else if (input.startsWith("todo")) {
            String[] parts = input.split(" ");
            String[] description = Arrays.copyOfRange(parts, 1, parts.length);
            tasks.addTask(description, TaskType.TODO, null, null, null);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.split("/by");
            if (parts.length == 2) {
                String[] description = parts[0].trim().split(" ", 2);
                tasks.addTask(new String[]{description[1]}, TaskType.DEADLINE, new String[]{parts[1]}, null, null);
            } else {
                throw new JudyException("Invalid deadline format. Use: deadline <description> /by <time>");
            }
        } else if (input.startsWith("event")) {
            String[] parts = input.split("/from |/to ");
            if (parts.length == 3) {
                String[] description = parts[0].trim().split(" ", 2);
                tasks.addTask(new String[]{description[1]}, TaskType.EVENT, null, new String[]{parts[1]}, new String[]{parts[2]});
            } else {
                throw new JudyException("Invalid event format. Use: event <description> /from <start> /to <end>");
            }
        } else if (input.startsWith("delete")) {
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                int number = Integer.parseInt(parts[1]);
                tasks.deleteTask(number);
            } else {
                throw new JudyException("Invalid delete format. Use: delete <index>");
            }
        } else {
            throw new JudyException("Unknown command. Please try again with a valid command.");
        }
    }
}


