package Judy.ui;

public class Ui {
    private static final String logo = "Judy";
    public Ui() { }

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
