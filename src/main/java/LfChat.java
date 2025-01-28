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
    private static final String FILE_PATH = "data/LFChat.txt";

    public static void main(String[] args) throws MissingDescriptionException {
        String logo = "  _      ______   _____ _           _\n"
                + " | |    |  ____| |  __ || |         | |\n"
                + " | |    | |__    | |    | |__   __| |\n"
                + " | |    |  __|   | |    | '_ \\ / _` |\n"
                + " | |____| |      | |___ | | | | (_| |_\n"
                + " |______|_|      |_____||_| |_|\\__,_(_)\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println(" Hello! I'm LFChat");
        System.out.println(" What can I do for you?");
        printLine();
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        Scanner scanner = new Scanner(System.in);
        String userInput;
        EnsureDirectoryExists();
        loadTasksFromFile();



        while (true) {
            userInput = scanner.nextLine();
            Task task;
            String commandWord = userInput.split(" ")[0].toUpperCase();

            try {
                Command command = Command.valueOf(commandWord);
                command.execute(userInput);

                if (command == Command.BYE) {
                    break;
                }
            } catch (MissingDescriptionException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            } catch (IllegalArgumentException e) {
                printLine();
                System.out.println(" Sorry, the command does not exist :<");
                printLine();
            }

        }

        scanner.close();

    }
    enum Command {
        BYE {
            @Override
            void execute(String input) {
                bye();
            }
        },
        LIST {
            @Override
            void execute(String input) {
                listTasks(listOfTasks);
            }
        },
        MARK {
            @Override
            void execute(String input) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                markAsDone(listOfTasks, taskNumber);
            }
        },
        UNMARK {
            @Override
            void execute(String input) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                markAsUndone(listOfTasks, taskNumber);
            }
        },
        TODO {
            @Override
            void execute(String input) throws MissingDescriptionException{
                String description = input.substring(5).trim();
                Task task = new ToDos(description);
                addPrintTask(listOfTasks, task);
            }
        },
        DEADLINE {
            @Override
            void execute(String input) throws MissingDescriptionException{
                String description = input.substring(9, input.indexOf("/by")).trim();
                String deadline = input.substring(input.indexOf("/by") + 4).trim();
                Task task = new Deadlines(description, deadline);
                addPrintTask(listOfTasks, task);
            }
        },
        EVENT {
            @Override
            void execute(String input) throws MissingDescriptionException{
                String description = input.substring(6, input.indexOf("/from")).trim();
                String startTime = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
                String endTime = input.substring(input.indexOf("/to") + 4).trim();
                Task task = new Events(description, startTime, endTime);
                addPrintTask(listOfTasks, task);
            }
        },
        DELETE {
            @Override
            void execute(String input) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                deleteTask(listOfTasks, taskNumber);
            }
        };
        abstract void execute(String input) throws MissingDescriptionException;

    }

    public static void listTasks(ArrayList<Task> listOfTasks) {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + listOfTasks.get(i).toString());
        }
        printLine();
    }

    public static void bye() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void markAsDone(ArrayList<Task> listOfTasks, int taskNumber) {
        if (taskNumber <= 0 || taskNumber > listOfTasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        listOfTasks.get(taskNumber - 1).markAsDone();

        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + listOfTasks.get(taskNumber - 1).toString());
        printLine();
    }

    public static void deleteTask(ArrayList<Task> listOfTasks, int taskNumber) {
        if (taskNumber <= 0 || taskNumber > listOfTasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + listOfTasks.get(taskNumber - 1).toString());
        listOfTasks.remove(taskNumber - 1);
        System.out.println(" Now you have " + listOfTasks.size() + " tasks in the list.");
        printLine();
    }
    public static void markAsUndone(ArrayList<Task> listOfTasks, int taskNumber) {
        if (taskNumber <= 0 || taskNumber > listOfTasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        listOfTasks.get(taskNumber - 1).markAsUndone();

        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + listOfTasks.get(taskNumber - 1).toString());
        printLine();
    }

    public static void addPrintTask(ArrayList<Task> listOfTasks, Task task) throws MissingDescriptionException {
        if (task.getDescription().isEmpty()) {
            throw new MissingDescriptionException("Description for the tasks cannot be empty.");
        }
        listOfTasks.add(task);
        saveTaskToFile(task);
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println(" Now you have " + listOfTasks.size() + " tasks in the list.");
        printLine();
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void EnsureDirectoryExists() {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }

    private static void saveTaskToFile(Task task) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task t : listOfTasks) {
                writer.write(t.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static void loadTasksFromFile() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) {
            return;
        }
        System.out.println("Loading tasks from: " + path.toAbsolutePath());

        try {
            Files.lines(path).forEach(line -> {
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    listOfTasks.add(task);
                }
            });
            System.out.println("Tasks loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }


}
