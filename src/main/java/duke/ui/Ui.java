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
     * Returns the welcome message and application logo at startup.
     */
    public static String start() {
        String logo = "  _      ______   _____ _           _\n"
                + " | |    |  ____| |  __ || |         | |\n"
                + " | |    | |__    | |    | |__   __| |\n"
                + " | |    |  __|   | |    | '_ \\ / _` |\n"
                + " | |____| |      | |___ | | | | (_| |_\n"
                + " |______|_|      |_____||_| |_|\\__,_(_)\n";
        return "Hello! I'm LFChat\n"
                + "What can I do for you?\n";
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param listOfTasks The list of tasks.
     * @param taskNumber The index of the task that was marked as done.
     */
    public static String markAsDonePrint(ArrayList<Task> listOfTasks, int taskNumber) {
        Task task = listOfTasks.get(taskNumber - 1);
        return " Nice! I've marked this task as done:\n"
                + "  " + task + "\n";
    }

    /**
     * Prints a message when a task is marked as not done.
     *
     * @param listOfTasks The list of tasks.
     * @param taskNumber The index of the task that was marked as not done.
     */
    public static String markAsUndonePrint(ArrayList<Task> listOfTasks, int taskNumber) {
        Task task = listOfTasks.get(taskNumber - 1);
        return " OK, I've marked this task as not done yet:\n"
                + "  " + task + "\n";
    }

    /**
     * Prints a message when a task is deleted.
     *
     * @param listOfTasks The list of tasks.
     * @param taskToDelete The task that was deleted.
     */
    public static String deleteTaskPrint(ArrayList<Task> listOfTasks, Task taskToDelete) {
        return " Noted. I've removed this task:\n"
                + "  " + taskToDelete + "\n"
                + " Now you have " + (listOfTasks.size()) + " tasks in the list.\n";
    }

    /**
     * Prints a message when a task is added.
     *
     * @param listOfTasks The list of tasks.
     * @param task        The task that was added.
     * @throws MissingDescriptionException If the task description is missing.
     */
    public static String addTaskPrint(ArrayList<Task> listOfTasks, Task task) throws MissingDescriptionException {
        if (task.getDescription().isEmpty()) {
            throw new MissingDescriptionException("Description for the tasks cannot be empty.");
        }
        return " Got it. I've added this task:\n"
                + "  " + task + "\n"
                + " Now you have " + listOfTasks.size() + " tasks in the list.\n";
    }

    /**
     * Prints the goodbye message when the user exits the application.
     */
    public static String bye() {
        return " Bye. Hope to see you again soon!\n";
    }

    /**
     * Lists all tasks currently stored in the task list.
     *
     * @param listOfTasks The list of tasks to be displayed.
     */
    public static String listTasks(ArrayList<Task> listOfTasks) {
        StringBuilder tasksList = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            tasksList.append(" ").append(i + 1).append(".").append(listOfTasks.get(i).toString()).append("\n");
        }
        return tasksList.toString();
    }

    /**
     * Finds and displays tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @param listOfTasks The list of tasks to search within.
     */
    public static String findTasks(String keyword, ArrayList<Task> listOfTasks) {
        StringBuilder matchingTasks = new StringBuilder(" Here are the matching tasks in your list:\n");
        int count = 1;
        for (Task task : listOfTasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.append(count).append(".").append(task).append("\n");
                count += 1;
            }
        }
        return matchingTasks.toString();
    }
}
