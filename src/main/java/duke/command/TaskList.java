package duke.command;

import java.util.ArrayList;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingDescriptionException;
import duke.tasks.Task;

/**
 * The {@code TaskList} class manages a list of tasks. It provides methods for adding tasks,
 * marking tasks as done or undone, and deleting tasks. Each task in the list is identified by a
 * task number, which corresponds to its position in the list.
 *
 * <p>Methods in this class ensure that task operations are performed safely, throwing exceptions when necessary.
 */
public class TaskList {

    /**
     * Checks if the given task number is valid in the context of the provided task list.
     * A task number is considered valid if it is greater than 0 and less than or equal to the size of the task list.
     *
     * @param listOfTasks The list of tasks to check against.
     * @param taskNumber The task number to validate.
     * @throws InvalidTaskNumberException If the task number is invalid (less than 1 or greater than the list size).
     */
    private static void checkIfTaskIsValid(ArrayList<Task> listOfTasks, int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber <= 0 || taskNumber > listOfTasks.size()) {
            throw new InvalidTaskNumberException("Invalid task number!");
        }
    }

    /**
     * Marks the task at the specified task number as done.
     *
     * @param listOfTasks The list of tasks to operate on.
     * @param taskNumber The task number of the task to mark as done.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static void markAsDone(ArrayList<Task> listOfTasks, int taskNumber) throws InvalidTaskNumberException {
        checkIfTaskIsValid(listOfTasks, taskNumber);
        listOfTasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Marks the task at the specified task number as undone.
     *
     * @param listOfTasks The list of tasks to operate on.
     * @param taskNumber The task number of the task to mark as undone.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static void markAsUndone(ArrayList<Task> listOfTasks, int taskNumber) throws InvalidTaskNumberException {
        checkIfTaskIsValid(listOfTasks, taskNumber);
        listOfTasks.get(taskNumber - 1).markAsUndone();
    }

    /**
     * Deletes the task at the specified task number from the task list.
     *
     * @param listOfTasks The list of tasks to operate on.
     * @param taskNumber The task number of the task to delete.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static void deleteTask(ArrayList<Task> listOfTasks, int taskNumber) throws InvalidTaskNumberException {
        checkIfTaskIsValid(listOfTasks, taskNumber);
        listOfTasks.remove(taskNumber - 1);
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param listOfTasks The list of tasks to add the new task to.
     * @param task The task to add.
     * @throws MissingDescriptionException If the task description is empty.
     */
    public static void addTask(ArrayList<Task> listOfTasks, Task task) throws MissingDescriptionException {
        if (task.getDescription().isEmpty()) {
            throw new MissingDescriptionException("Description for the tasks cannot be empty.");
        }
        listOfTasks.add(task);
    }
}
