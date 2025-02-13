package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.command.Command;
import duke.command.Storage;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingDescriptionException;
import duke.parsers.Parser;
import duke.tasks.Task;
import javafx.application.Platform;

public class Cinnamoroll {
    private ArrayList<Task> listOfTasks = new ArrayList<>();

    public Cinnamoroll() {
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
        } catch (MissingDescriptionException | IllegalArgumentException | InvalidTaskNumberException |
                 DateTimeParseException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a valid task number!";
        }
    }
}
