package duke.ui;

import java.util.ArrayList;

import duke.exceptions.MissingDescriptionException;
import duke.tasks.Task;

/**
 * The {@code Ui} class handles all user interactions by displaying messages and responses.
 * It provides methods to print formatted messages for different user actions in the Duke application.
 */
public class Ui {
    /**
     * Prints a horizontal line separator for better readability in the console.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the welcome message and application logo at startup.
     */
    public static void start() {
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
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param listOfTasks The list of tasks.
     * @param taskNumber The index of the task that was marked as done.
     */
    public static void markAsDonePrint(ArrayList<Task> listOfTasks, int taskNumber) {

        Task task = listOfTasks.get(taskNumber - 1);
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

    /**
     * Prints a message when a task is marked as not done.
     *
     * @param listOfTasks The list of tasks.
     * @param taskNumber The index of the task that was marked as not done.
     */
    public static void markAsUndonePrint(ArrayList<Task> listOfTasks, int taskNumber) {
        Task task = listOfTasks.get(taskNumber - 1);
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printLine();
    }

    /**
     * Prints a message when a task is deleted.
     *
     * @param listOfTasks The list of tasks.
     * @param taskNumber The index of the task that was deleted.
     */
    public static void deleteTaskPrint(ArrayList<Task> listOfTasks, int taskNumber) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + listOfTasks.get(taskNumber - 1));
        System.out.println(" Now you have " + (listOfTasks.size() - 1) + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message when a task is added.
     *
     * @param listOfTasks The list of tasks.
     * @param task        The task that was added.
     * @throws MissingDescriptionException If the task description is missing.
     */
    public static void addTaskPrint(ArrayList<Task> listOfTasks, Task task) throws MissingDescriptionException {
        if (task.getDescription().isEmpty()) {
            throw new MissingDescriptionException("Description for the tasks cannot be empty.");
        }
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(" Now you have " + listOfTasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Prints the goodbye message when the user exits the application.
     */
    public static void bye() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Lists all tasks currently stored in the task list.
     *
     * @param listOfTasks The list of tasks to be displayed.
     */
    public static void listTasks(ArrayList<Task> listOfTasks) {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + listOfTasks.get(i).toString());
        }
        printLine();
    }

    /**
     * Finds and displays tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @param listOfTasks The list of tasks to search within.
     */
    public static void findTasks(String keyword, ArrayList<Task> listOfTasks) {
        int count = 1;
        printLine();
        System.out.println(" Here are the matching tasks in your list:");
        for (Task task : listOfTasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(count + "." + task);
                count += 1;
            }
        }
        printLine();
    }
}
