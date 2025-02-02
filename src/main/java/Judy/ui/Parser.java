package Judy.ui;
import Judy.command.*;
import Judy.util.*;
import Judy.task.*;
import java.util.Arrays;

public class Parser {

    /**
     * Parses the input message.
     *
     * @param input    input message by the user.
     * @throws JudyException if there's an error during task creation
     */
    public static Command parse(String input) throws JudyException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            String[] parts = input.split(" ");
            if (parts.length > 1) {
                int number = Integer.parseInt(parts[1]);
                boolean isMark = input.startsWith("mark");
                return new MarkCommand(number, isMark);
            } else {
                throw new JudyException("Invalid mark command. Usage: mark <task number>");
            }
        } else if (input.startsWith("todo")) {
            String[] description = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);
            return new AddTaskCommand(description, TaskType.TODO, null, null, null);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.split("/by");
            if (parts.length == 2) {
                String[] description = parts[0].trim().split(" ", 2);
                return new AddTaskCommand(new String[]{description[1]}, TaskType.DEADLINE, new String[]{parts[1].trim()}, null, null);
            } else {
                throw new JudyException("Invalid deadline format. Use: deadline <description> /by <time>");
            }
        } else if (input.startsWith("event")) {
            String[] parts = input.split("/from |/to ");
            if (parts.length == 3) {
                String[] description = parts[0].trim().split(" ", 2);
                return new AddTaskCommand(new String[]{description[1]}, TaskType.EVENT, null, new String[]{parts[1].trim()}, new String[]{parts[2]});
            } else {
                throw new JudyException("Invalid event format. Use: event <description> /from <start> /to <end>");
            }
        } else if (input.startsWith("delete")) {
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                int number = Integer.parseInt(parts[1]);
                return new DeleteCommand(number);
            } else {
                throw new JudyException("Invalid delete format. Use: delete <index>");
            }
        } else {
            throw new JudyException("Unknown command. Please try again with a valid command.");
        }
    }
}
