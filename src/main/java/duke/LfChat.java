package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.command.Storage;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingDescriptionException;
import duke.parsers.Parser;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * The LfChat class represents the main application logic for a task management chatbot.
 * It initializes the user interface, loads tasks from storage, processes user commands,
 * and saves tasks before exiting.
 */
public class LfChat {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    public static void main(String[] args) {
        Ui.start();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Storage.ensureDirectoryExists();
        Storage.loadTasksFromFile(listOfTasks);


        while (true) {
            userInput = scanner.nextLine();
            try {
                Command command = Parser.parse(userInput);
                command.execute(userInput, listOfTasks);

                if (command == Command.BYE) {
                    break;
                }
            } catch (MissingDescriptionException | IllegalArgumentException | InvalidTaskNumberException e) {
                Ui.printLine();
                System.out.println(e.getMessage());
                Ui.printLine();
            }
        }
        Storage.saveTasksToFile(listOfTasks);
        scanner.close();

    }
}
