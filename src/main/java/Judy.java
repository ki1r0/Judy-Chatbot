public class Judy {
    static String separator = "____________________________________________________________\n";
    static String[] list = new String[100];
    static int counter = 0;

    private static void printList() {
        int size = list.length;
        int index = 1;
        for (int i = 0; i < size; i++) {
            if (list[i] != null) {
                System.out.println(index + ". " + list[i]);
                index++;
            }
        }
        System.out.println(separator);
    }
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        String logo = "Judy";
        System.out.println(separator + " Hello! I'm " + logo + "\n" +
                " What can I do for you? \n" + separator);

        String message = inputHandler.getMessage();
        while (!message.equals("bye")) {
            if (message.equals("list")) {
                printList();
            } else {
                list[counter] = message;
                counter++;
                System.out.println("added: " + message + "\n" + separator);
            }
            message = inputHandler.getMessage();
        }
        System.out.println(" Bye. Hope to see you again soon! \n" + separator);
    }
}
