public class Judy {

    static String separator = "____________________________________________________________\n";
    static int size = 100;
    static Task[] list = new Task[size];
    static int counter = 0;

    private static void printResponse(String message) {
        String separator = "    ____________________________________________________________";
        System.out.println(separator);
        System.out.println("    " + message);
        System.out.println(separator);
    }
    private static void printList() {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (int i = 0; i < size; i++) {
            if (list[i] != null) {
                response.append("    ").append(index).append(". ").append(list[i].toString());
                if (i < size - 1 && list[i + 1] != null) { // Add newline only if there's another task
                    response.append("\n");
                }
                index++;
            }
        }
        // If there are no tasks, add a message indicating an empty list
        if (index == 1) {
            response.append("    No tasks found in the list.");
        }
        printResponse(response.toString()); // Use printResponse for consistent formatting
    }

    private static void addTask(String input) {
        list[counter] = new Task(input);
        counter++;
        printResponse("added: " + input);
    }

    private static void updateStatus (int index, boolean isMark) {
        Task task = list[index - 1];
        task.setStatus(isMark);
        String message = isMark ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        printResponse(message + "\n" + "    " + task.toString());
    }

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        String logo = "Judy";
        printResponse("Hello! I'm " + logo + "\n" +
                "    What can I do for you?");

        String input = inputHandler.getMessage();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                if (parts.length > 1) {
                    try {
                        int number = Integer.parseInt(parts[1]);
                        boolean isMark = input.startsWith("mark");
                        updateStatus(number, isMark);
                    } catch (Exception e) {
                        printResponse("Invalid task number. Please enter a valid number.");
                    }
                } else {
                    printResponse("Invalid mark command. Usage: mark <task number>");
                }
            } else {
                addTask(input);
            }
            input = inputHandler.getMessage();
        }
        printResponse(" Bye. Hope to see you again soon!");
    }
}

