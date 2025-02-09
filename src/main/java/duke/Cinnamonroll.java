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
import javafx.application.Platform;

public class Cinnamonroll {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    public Cinnamonroll() {
        Storage.ensureDirectoryExists();
        Storage.loadTasksFromFile(listOfTasks);
        assert listOfTasks != null : "listOfTasks should still not be null after loading";
    }
    public String processUserInput(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(input, listOfTasks);

            Storage.saveTasksToFile(listOfTasks);
            assert listOfTasks != null : "listOfTasks should not be null after saving";

            if (command == Command.BYE) {
                Platform.exit();
            }

            return response;
        } catch (MissingDescriptionException | IllegalArgumentException | InvalidTaskNumberException e) {
            return e.getMessage();
        }
    }
}
