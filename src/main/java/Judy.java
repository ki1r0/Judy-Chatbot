public class Judy {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        String separator = "____________________________________________________________\n";
        String logo = "Judy";
        System.out.println(separator + " Hello! I'm " + logo + "\n" +
                " What can I do for you? \n" + separator);

        String message = inputHandler.getMessage();
        while (!message.equals("bye")) {

            System.out.println(message + "\n" + separator);
            message = inputHandler.getMessage();
        }
        System.out.println(" Bye. Hope to see you again soon! \n" + separator);
    }
}
