import java.util.ArrayList;
import java.util.Arrays;

public class Judy {
    private static final Storage storage = new Storage("./data/judy.txt");
    private static final TaskList tasks = new TaskList(storage);
    //private Ui ui;
    //private Parser parser;

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        String logo = "Judy";
        Util.printResponse("Hello! I'm " + logo + "\n" +
                "    What can I do for you?");

        while (true) {
            String input = inputHandler.getMessage();
            if (input.equals("bye")) {
                Util.printResponse(" Bye. Hope to see you again soon!");
                break;
            }
            handleCommand(input);
        }
        storage.saveTasks(tasks.getTasks());
    }

    private static void handleCommand(String input) {
            try {
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
                        throw new JudyException("Invalid event format. Use: deadline <description> /by <time>");
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
            } catch (JudyException e) {
                Util.printError(e.getMessage());
            } catch (NumberFormatException e) {
                Util.printError("Invalid number format. Please provide a valid task number.");
            }
        }
    }


