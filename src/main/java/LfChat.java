import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;


public class LfChat {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    public static void main(String[] args) {
        Ui.start();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Storage.EnsureDirectoryExists();
        Storage.loadTasksFromFile(listOfTasks);


        while (true) {
            userInput = scanner.nextLine();
            try {
                Command command = Parser.parse(userInput);
                command.execute(userInput, listOfTasks);

                if (command == Command.BYE) {
                    break;
                }
            } catch (MissingDescriptionException | IllegalArgumentException |InvalidTaskNumberException e) {
                Ui.printLine();
                System.out.println(e.getMessage());
                Ui.printLine();
            }
        }
        Storage.saveTasksToFile(listOfTasks);
        scanner.close();

    }
}
