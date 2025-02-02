public class Ui {
    private final String logo = "Judy";
    private InputHandler inputHandler;
    public Ui() {
        inputHandler = new InputHandler();
    }
    public void showLoadingError() {
        this.inputHandler = new InputHandler();
    }

    public void showWelcome() {
        Util.printResponse("Hello! I'm " + logo + "\n" +
                "    What can I do for you?");
    }

    public void showEnd() {
        Util.printResponse(" Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        Util.printError(message);
    }

    public String readInput() {
        return inputHandler.getMessage();
    }
}
