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
    public static String printHorizontalLine() {
        String horizontalLine = "-";
        for (int i = 0; i < 30; i++) {
            horizontalLine = horizontalLine.concat("-");
        }
        return horizontalLine;
    }

    /**
     * Prints the greeting at the start of the program
     */
    public String greet() {
        String greeting = String.format("%s\nHello! I'm %s, What can I do for you?\n%s",
                printHorizontalLine(), getBotName(), printHorizontalLine());
        return greeting;
    }

    /**
     * Prints the farewell at the end of the program
     */
    public String exit() {
        return String.format("Bye. Hope to see you again soon!\n%s", printHorizontalLine());
    }
}
