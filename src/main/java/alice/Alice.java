package alice;

import alice.command.Command;
import alice.exceptions.AliceException;

import java.util.Scanner;
import java.nio.file.Path;

public class Alice {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Alice(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);// Initialise scanner

        // initial greeting from AliceBot
        ui.greet();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = scanner.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AliceException e) {
                ui.printHorizontalLine();
                System.out.println(e.getMessage());
                ui.printHorizontalLine();
            }
        }
    }

    public static void main(String[] args) {
        String filePath = Path.of("data", "alice.txt").toString();
        new Alice(filePath).run();
    }
}
