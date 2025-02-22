package Judy.ui;

/**
 * Handles the user interface for the Judy chatbot.
 */
public class Ui {
    private static final String logo = "Judy";
    public Ui() { }

    /**
     * Displays the welcome message for the user.
     *
     * @return The welcome message string.
     */
    public static String showWelcome() {
        return "Hello! I'm " + logo + "\n"
                + "    What can I do for you?";
    }

    public String showEnd() {
        return " Bye. Hope to see you again soon!";
    }

    public String showError(String message) {
        return "Error: " + message;
    }
}
