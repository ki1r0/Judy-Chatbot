public class Util {
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
