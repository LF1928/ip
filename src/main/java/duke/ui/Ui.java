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
     * Returns the welcome message at startup.
     */
    public static String start() {
        return "Hellu, sunshine! \n"
                + " Cinnamoroll is here to help you stay organized! \n"
                + "Let's tackle your to-dos one cozy step at a time~ \n"
                + "What's on your mind today?";
    }

    /**
     * Returns the help instructions at startup.
     */
    public static String help() {
        return "Here are the commands you can use: \n"
                + "Add a different Tasks: \n"
                + "\"todo {description}\" \n"
                + "\"deadline {description} /by dd/mm/yyy\" \n"
                + "\"event {description} /from d/M/yyyy HHmm /to d/M/yyyy HHmm\" \n"
                + "Delete a Task: \"delete {task number}\" \n"
                + "View Tasks: \"list\" \n"
                + "Mark a Task: \"mark {task number}\" \n"
                + "Find Tasks with keywords: \"find {keywords}\" \n"
                + "Unmark a Task: \"unmark {task number}\" \n"
                + "Clone a Task: \"clone {task number}\" \n"
                + "Update description of a Task: \"update {task number} {description}\" \n"
                + "Try it out!";
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param listOfTasks The list of tasks.
     * @param taskNumber The index of the task that was marked as done.
     */
    public static String markAsDonePrint(ArrayList<Task> listOfTasks, int taskNumber) {
        Task task = listOfTasks.get(taskNumber - 1);
        return " Yaaay! You did it!\n"
                + "  " + task + " is all done and dusted! You're amazing!";
    }

    /**
     * Prints a message when a task is marked as not done.
     *
     * @param listOfTasks The list of tasks.
     * @param taskNumber The index of the task that was marked as not done.
     */
    public static String markAsUndonePrint(ArrayList<Task> listOfTasks, int taskNumber) {
        Task task = listOfTasks.get(taskNumber - 1);
        return " Oopsie-daisy!\n"
                + "  " + task + "\n "
                + " is back on the to-do list! No worries, You've got this at your own pace! \uD83C\uDF1F";
    }

    /**
     * Prints a message when a task is deleted.
     *
     * @param listOfTasks The list of tasks.
     * @param taskToDelete The task that was deleted.
     */
    public static String deleteTaskPrint(ArrayList<Task> listOfTasks, Task taskToDelete) {
        return " Poof! \n"
                + "  " + taskToDelete + "\n"
                + " is gone! Like a soft cloud drifting away~ \n"
                + " Now you have only " + (listOfTasks.size()) + " tasks in the list!\n";
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
        return " Ooooh, a new task!\n"
                + "  " + task + "\n"
                + " has been added! You got this, sweet bun!\n"
                + " Now you have " + listOfTasks.size() + " tasks in the list!\n";
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
        StringBuilder tasksList = new StringBuilder(" Here's your to-do list, sweet pea! \uD83C\uDF38 \n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            tasksList.append(" ").append(i + 1).append(".").append(listOfTasks.get(i).toString()).append("\n");
        }
        tasksList.append("Keep going, you're doing great! \uD83C\uDF6A\uD83D\uDCAA");
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

    /**
     * Prints a message when a task is updated.
     *
     * @param updatedTask The new Task.
     * @param oldDescription The description that was to be updated.
     */
    public static String updateTaskDescriptionPrint(Task updatedTask, String oldDescription) {
        return "Got it, sweet pea! \n" + oldDescription + " has been updated to " + updatedTask.getDescription()
                + " ! \n"
                + "Fresh and fluffy like a warm pastry~ You're doing amazing!";
    }

    /**
     * Prints a message when a task is cloned.
     *
     * @param task the task to clone.
     */
    public static String cloneTaskPrint(Task task) {
        return "Okay! \n" + task + " has been duplicated! \n";
    }
}
