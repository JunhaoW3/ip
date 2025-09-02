package alice;

public class Ui {
    private static String BOT_NAME = "Alice";

    public String getBotName() {
        return BOT_NAME;
    }

    public void printHorizontalLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    public void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm " + getBotName());
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
