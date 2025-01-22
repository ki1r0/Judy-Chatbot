public class Duke {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        String dashline = "____________________________________________________________\n";
        String logo = "Judy";
        System.out.println(dashline + " Hello! I'm " + logo + "\n" +
                " What can I do for you? ");

        String message = inputHandler.getMessage();
        if (message.equals("")) {
            System.out.println(dashline + " Bye. Hope to see you again soon!");
        }


    }
}
