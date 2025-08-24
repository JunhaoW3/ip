import java.util.Scanner;

public class Alice {
    public static String botName = "Alice";

    // Getter for name
    public static String getBotName() {
        return botName;
    }

    // Printing horizontal lines
    public static void horizontalLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    // Exiting the chat
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        horizontalLine();
        System.out.println("Hello! I'm " + getBotName());
        System.out.println("What can I do for you?");
        horizontalLine();

        //Using lower case so that it accepts different iterations of "bye"
        String text = scanner.nextLine();
        String lowerCase = text.toLowerCase();

        while (!lowerCase.equals("bye")) {
            horizontalLine();
            System.out.println(text);
            horizontalLine();
            text = scanner.nextLine();
            lowerCase = text.toLowerCase();
        }

        horizontalLine();
        exit();
    }
}
