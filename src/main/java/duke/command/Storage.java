package duke.command;

import duke.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "data/LFChat.txt";
    public static void EnsureDirectoryExists() {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }

    public static void saveTasksToFile(ArrayList<Task> listOfTasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task t : listOfTasks) {
                writer.write(t.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

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
