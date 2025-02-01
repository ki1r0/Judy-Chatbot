import java.util.ArrayList;
import java.util.Arrays;

public class Judy {
    static ArrayList<Task> list = new ArrayList<>();

    private static void printResponse(String message) {
        String separator = "    ____________________________________________________________";
        System.out.println(separator);
        System.out.println("    " + message);
        System.out.println(separator);
    }

    private static void printError(String errorMessage) {
        printResponse("ERROR: " + errorMessage);
    }

    private static void printList() {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : list) {
            response.append("    ").append(index).append(". ").append(task.toString());
            index++;
        }

        if (list.isEmpty()) {
            response.append("    No tasks found in the list.");
        }
        printResponse(response.toString()); // Use printResponse for consistent formatting
    }

    private static void addTask(String[] description, TaskType type, String[] deadline, String[] start, String[] end) {
        if (description.length == 0) {
            printError("The description cannot be empty. Please provide a valid description.");
            return;
        }

        Task task = null;
        switch (type) {
            case TODO:
                task = new Todo(String.join(" ", description));
                break;
            case DEADLINE:
                task = new Deadline(String.join(" ", description), String.join(" ", deadline));
                break;
            case EVENT:
                task = new Event(String.join(" ", description), String.join(" ", start), String.join(" ", end));
                break;
            default:
                printError("Unknown task type. Please try again.");
                return;
        }

        list.add(task);
        String response = String.format("Got it. I've added this task:\n" +
                    "      %s\n" +
                    "    Now you have %d tasks in the list.", task, list.size());
        printResponse(response);
    }

    private static void deleteTask(int index) {
        if (index < 1 || index > list.size()) {
            printError("Invalid task number. Please provide a number between 1 and " + list.size() + ".");
            return;
        }

        Task task = list.get(index - 1);
        list.remove(index - 1);
        String response = String.format("Noted. I've removed this task:\n" +
                "      %s\n" +
                "    Now you have %d tasks in the list.", task, list.size());
        printResponse(response);
    }

    private static void updateStatus (int index, boolean isMark) {
        if (index < 1 || index > list.size()) {
            printError("Invalid task number. Please provide a number between 1 and " + list.size() + ".");
            return;
        }
        Task task = list.get(index - 1);
        task.setStatus(isMark);
        String message = isMark ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        printResponse(message + "\n" + "    " + task);
    }

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        String logo = "Judy";
        printResponse("Hello! I'm " + logo + "\n" +
                "    What can I do for you?");

        String input = inputHandler.getMessage();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    printList();
                } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    String[] parts = input.split(" ");
                    if (parts.length > 1) {
                        int number = Integer.parseInt(parts[1]);
                        boolean isMark = input.startsWith("mark");
                        updateStatus(number, isMark);
                    } else {
                        throw new JudyException("Invalid mark command. Usage: mark <task number>");
                    }

                } else if (input.startsWith("todo")) {
                    String[] parts = input.split(" ");
                    String[] description = Arrays.copyOfRange(parts, 1, parts.length);
                    addTask(description, TaskType.TODO, null, null, null);

                } else if (input.startsWith("deadline")) {
                    String[] parts = input.split("/");
                    if (parts.length == 2) {
                        String[] firstHalf = parts[0].split(" ");
                        String[] description = Arrays.copyOfRange(firstHalf, 1, firstHalf.length);
                        String[] secondHalf = parts[1].split(" ");
                        String[] deadline = Arrays.copyOfRange(secondHalf, 1, secondHalf.length);
                        addTask(description, TaskType.DEADLINE, deadline, null, null);
                    } else {
                        throw new JudyException("Invalid deadline format. Use: deadline <description> /by <time>");
                    }

                } else if (input.startsWith("event")) {
                    String[] parts = input.split("/");
                    if (parts.length == 3) {
                        String[] first = parts[0].split(" ");
                        String[] description = Arrays.copyOfRange(first, 1, first.length);
                        String[] second = parts[1].split(" ");
                        String[] start = Arrays.copyOfRange(second, 1, second.length);
                        String[] third = parts[2].split(" ");
                        String[] end = Arrays.copyOfRange(third, 1, third.length);
                        addTask(description, TaskType.EVENT, null, start, end);
                    } else {
                        throw new JudyException("Invalid event format. Use: deadline <description> /by <time>");
                    }

                } else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ");
                    if (parts.length == 2) {
                        int number = Integer.parseInt(parts[1]);
                        deleteTask(number);
                    } else {
                        throw new JudyException("Invalid delete format. Use: delete <index>");
                    }

                } else {
                    throw new JudyException("Unknown command. Please try again with a valid command.");
                }
            } catch (JudyException e) {
                printError(e.getMessage());
            } catch (NumberFormatException e) {
                printError("Invalid number format. Please provide a valid task number.");
            }
            input = inputHandler.getMessage();
        }
        printResponse(" Bye. Hope to see you again soon!");
    }
}

