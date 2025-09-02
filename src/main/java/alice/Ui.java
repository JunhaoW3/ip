package alice;

/**
 * Represents the Ui that deals with interactions with the users
 */
public class Ui {
    private static String BOT_NAME = "Alice";

    /**
     * Returns the name of the bot
     *
     * @return Bot name
     */
    public String getBotName() {
        return BOT_NAME;
    }

    /**
     * Prints the horizontal line that separates user input from bot output
     */
    public void horizontalLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    /**
     * Prints the greeting at the start of the program
     */
    public void greet() {
        horizontalLine();
        System.out.println("Hello! I'm " + getBotName());
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    /**
     * Prints the farewell at the end of the program
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }
}
