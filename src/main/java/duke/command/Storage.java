package duke.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.tasks.Task;



/**
 * The {@code Storage} class handles the saving and loading of task data to and from a file.
 * It ensures that the appropriate directory exists and provides methods for saving tasks
 * to a file and loading tasks from a file.
 * The file used for storage is located at "data/LFChat.txt".
 * Methods in this class handle file-related operations and manage task data serialization and deserialization.
 */
public class Storage {

    // The path to the file used for storing tasks
    private static final String FILE_PATH = "data/LFChat.txt";

    /**
     * Ensures that the directory for storing task files exists.
     * If the directory does not exist, it is created.
     */
    public static void ensureDirectoryExists() {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }

    /**
     * Saves a list of tasks to a file.
     * Each task is written to the file using its {@code toFileFormat} method.
     *
     * @param listOfTasks The list of tasks to be saved.
     */
    public static void saveTasksToFile(ArrayList<Task> listOfTasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task t : listOfTasks) {
                writer.write(t.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file and adds them to the provided list of tasks.
     * The tasks are read from the file and parsed using the {@code fromFileFormat} method.
     * If the file does not exist or an error occurs, the task list is unaffected.
     *
     * @param listOfTasks The list to which the loaded tasks will be added.
     */
    public static void loadTasksFromFile(ArrayList<Task> listOfTasks) {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) {
            return;
        }
        try {
            Files.lines(path).forEach(line -> {
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    listOfTasks.add(task);
                }
            });
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
