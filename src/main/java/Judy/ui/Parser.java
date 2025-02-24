package Judy.ui;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import Judy.command.AddCommand;
import Judy.command.Command;
import Judy.command.DeleteCommand;
import Judy.command.FindCommand;
import Judy.command.ListCommand;
import Judy.command.MarkCommand;
import Judy.task.TaskType;
import Judy.util.JudyException;


/**
 * Parses user input and converts date/time strings into formatted DateTime objects.
 */
public class Parser {
    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)
    };

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
            String description;
            if (input.length() < 5) {
                description = "";
            } else {
                description = input.substring(5);
            }
            return new AddCommand(TaskType.TODO, description, null, null, null);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.split("/by");
            assert parts.length > 0 : "Split parts should not be empty";
            String description;
            if (parts.length == 2) {
                if (parts[0].trim().length() < 9) {
                    description = "";
                } else {
                    description = parts[0].trim().substring(9);
                }
                String deadline = parseDateTime(parts[1].trim());
                return new AddCommand(TaskType.DEADLINE, description, deadline, null, null);
            } else {
                throw new JudyException("Invalid deadline format. Use: deadline <description> /by <time>");
            }

        } else if (input.startsWith("event")) {
            String[] parts = input.split(" /from | /to ");
            assert parts.length > 0 : "Split parts should not be empty";
            String description;
            if (parts.length == 3) {
                if (parts[0].trim().length() < 6) {
                    description = "";
                } else {
                    description = parts[0].trim().substring(6);
                }
                String start = parseDateTime(parts[1].trim());
                String end = parseDateTime(parts[2].trim());
                return new AddCommand(TaskType.EVENT, description, null, start, end);
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

    /**
     * Parses the date and time.
     *
     * @param dateTimes    the {@code TaskList} to which the task will be added
     */
    public static String parseDateTime(String... dateTimes) throws JudyException {
        if (dateTimes.length < 1 || dateTimes.length > 2) {
            throw new JudyException("Function accepts either one or two date arguments.");
        }
        LocalDateTime parsedA = parseToLocalDateTime(dateTimes[0].trim(), LocalDate.now());

        if (dateTimes.length == 1) {
            return formatDate(parsedA);
        }

        LocalDateTime parsedB = parseToLocalDateTime(dateTimes[1].trim(), parsedA.toLocalDate());
        return formatDate(parsedA) + " - " + formatDate(parsedB);
    }

    /**
     * Parses an input string into a {@link LocalDateTime} using various date/time formats.
     *
     * @param input     The input string representing a date or date-time.
     * @param reference A reference {@link LocalDate} used for relative date parsing.
     * @return A {@link LocalDateTime} object representing the parsed date/time.
     * @throws DateTimeParseException If the input format is invalid.
     */
    private static LocalDateTime parseToLocalDateTime(String input, LocalDate reference) throws JudyException {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                if (formatter.toString().contains("HHmm")) {
                    return LocalDateTime.parse(input, formatter);
                } else {
                    LocalDate date = LocalDate.parse(input, formatter);
                    return date.atStartOfDay();
                }
            } catch (DateTimeParseException ignored) { }
        }

        try {
            LocalDate nextDate = dayToDate(input, reference);
            return nextDate.atStartOfDay();
        } catch (IllegalArgumentException e) {
            throw new JudyException("Invalid date format, please use a valid date format.");
        }
    }

    /**
     * Converts a day name (e.g., "Monday") into a {@link LocalDate} relative to a given reference date.
     *
     * @param dayName       The name of the day (case-insensitive).
     * @param referenceDate The reference {@link LocalDate} from which the next occurrence is calculated.
     * @return A {@link LocalDate} representing the next occurrence of the specified day.
     * @throws IllegalArgumentException If the input is not a valid day name.
     */
    @SuppressWarnings("checkstyle:NeedBraces")
    private static LocalDate dayToDate(String dayName, LocalDate referenceDate) throws IllegalArgumentException {
        DayOfWeek targetDay = DayOfWeek.valueOf(dayName.toUpperCase());
        DayOfWeek todayDay = referenceDate.getDayOfWeek();
        int daysUntilTarget = (targetDay.getValue() - todayDay.getValue() + 7) % 7;
        if (daysUntilTarget == 0) daysUntilTarget = 7; // Ensure it is in the future
        return referenceDate.plusDays(daysUntilTarget);
    }

    /**
     * Formats a {@link LocalDateTime} object into a human-readable string.
     *
     * @param dateTime The {@link LocalDateTime} to format.
     * @return A formatted date string in the format "MMM d yyyy".
     */
    private static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
    }
}
