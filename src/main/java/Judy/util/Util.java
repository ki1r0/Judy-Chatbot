package Judy.util;

/**
 * A utility class providing helper methods for the Judy chatbot.
 */
public class Util {

    /**
     * Prints a formatted response with a separator for better readability.
     *
     * @param message The message to be printed within the formatted response.
     */
    public static void printResponse(String message) {
        String separator = "    ____________________________________________________________";
        System.out.println(separator);
        System.out.println("    " + message);
        System.out.println(separator);
    }

    public static void printError(String errorMessage) {
        printResponse("ERROR: " + errorMessage);
    }

}
