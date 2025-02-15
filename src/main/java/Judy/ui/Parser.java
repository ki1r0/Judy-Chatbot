package Judy.ui;

import Judy.command.*;
import Judy.util.*;
import Judy.task.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

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

    /**
     * Parses the date and time.
     *
     * @param dateTime    the {@code TaskList} to which the task will be added
     */
    public static String parseDateTime(String dateTime) {
        dateTime = dateTime.trim();
        DateTimeFormatter formatters[] = {
                DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };

        try {
            for (DateTimeFormatter formatter : formatters) {
                dateTime = dateTime.trim();
                try {
                    if (formatter.toString().contains("HHmm")) {
                        return LocalDateTime.parse(dateTime, formatter)
                                .format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
                    } else {
                        LocalDate date = LocalDate.parse(dateTime, formatter);
                        return date.atStartOfDay()
                                .format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
                    }
                } catch (DateTimeParseException e) {
                }
            }
            if (dateTime.length() < 2) {
                throw new IllegalArgumentException("Input must be at least two letters long.");
            }
            for (Day day : Day.values()) {
                if (day.name().startsWith(dateTime.toUpperCase()) && day.name().contains(dateTime.toUpperCase())) {
                    return dayToDate(day.name());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Invalid date format.");
        return "Invalid date";
    }

    private static String dayToDate(String dayName ) throws IllegalArgumentException {
        DayOfWeek targetDay = DayOfWeek.valueOf(dayName.toUpperCase());
        LocalDate today = LocalDate.now();
        DayOfWeek todayDay = today.getDayOfWeek();
        int daysUntilTarget = (targetDay.getValue() - todayDay.getValue() + 7) % 7;
        LocalDate resultDate = today.plusDays(daysUntilTarget);
        return resultDate.toString();
    }
}
