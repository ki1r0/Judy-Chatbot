package Judy.util;

import java.util.Scanner;
public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String getMessage() {
        System.out.print("");
        return scanner.nextLine();
    }

    public void close(){
        scanner.close();
    }
}
