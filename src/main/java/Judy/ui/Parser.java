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
        assert input != null : "Input command should not be null";

        if (input.equals("list")) {
            return new ListCommand();

        } else if (input.startsWith("find")) {
            String[] parts = input.split(" ");
            assert parts.length > 0 : "Split parts should not be empty";

            if (parts.length > 1) {
                return new FindCommand(parts[1].trim());
            } else {
                throw new JudyException("Invalid find command. Usage: find <keyword>");
            }

        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            String[] parts = input.split(" ");
            assert parts.length > 0 : "Split parts should not be empty";

            if (parts.length > 1) {
                int number = Integer.parseInt(parts[1]);
                boolean isMark = input.startsWith("mark");
                return new MarkCommand(number, isMark);
            } else {
                throw new JudyException("Invalid mark command. Usage: mark <task number>");
            }

        } else if (input.startsWith("todo")) {
            assert input.length() > 5 : "Todo command should have a description";
            String description = input.substring(5);
            return new AddCommand(TaskType.TODO, description, null, null, null);

        } else if (input.startsWith("deadline")) {
            String[] parts = input.split("/by");
            assert parts.length > 0 : "Split parts should not be empty";

            if (parts.length == 2) {
                String description = parts[0].trim().substring(9);
                assert !description.isEmpty() : "Deadline description should not be empty";
                assert !parts[1].trim().isEmpty() : "Deadline time should not be empty";

                return new AddCommand(TaskType.DEADLINE, description, parts[1].trim(), null, null);
            } else {
                throw new JudyException("Invalid deadline format. Use: deadline <description> /by <time>");
            }

        } else if (input.startsWith("event")) {
            String[] parts = input.split(" /from | /to ");
            System.out.println("Parsed Parts Length: " + parts.length); // Debugging

            for (int i = 0; i < parts.length; i++) {
                System.out.println("Part[" + i + "]: " + parts[i]);
            }

            assert parts.length > 0 : "Split parts should not be empty";

            if (parts.length == 3) {
                String description = parts[0].trim().substring(6);
                assert !description.isEmpty() : "Event description should not be empty";
                assert !parts[1].isEmpty() : "Event start time should not be empty";
                assert !parts[2].isEmpty() : "Event end time should not be empty";

                return new AddCommand(TaskType.EVENT, description, null, parts[1], parts[2]);
            } else {
                throw new JudyException("Invalid event format. Use: event <description> /from <start> /to <end>");
            }

        } else if (input.startsWith("delete")) {
            String[] parts = input.split(" ");
            assert parts.length > 0 : "Split parts should not be empty";

            if (parts.length == 2) {
                int number = Integer.parseInt(parts[1]);
                assert number > 0 : "Task number must be positive";
                return new DeleteCommand(number);
            } else {
                throw new JudyException("Invalid delete format. Use: delete <index>");
            }

        } else {
            throw new JudyException("Unknown command. Please try again with a valid command.");
        }
    }
}
