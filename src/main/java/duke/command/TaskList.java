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
     *
     * @param listOfTasks The list of tasks to check against.
     * @param taskNumber The task number to validate.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    private static void checkIfTaskIsValid(ArrayList<Task> listOfTasks, int taskNumber)
            throws InvalidTaskNumberException {
        if (taskNumber <= 0 || taskNumber > listOfTasks.size()) {
            throw new InvalidTaskNumberException("Please enter a valid task number!");
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
     */
    public static void deleteTask(ArrayList<Task> listOfTasks, int taskNumber) {
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

    /**
     * Updates a new description to the task.
     *
     * @param listOfTasks The list of tasks.
     * @param taskNumber The index of task to change.
     * @param newTaskDescription The new description of task.
     */
    public static Task updateTaskDescription(ArrayList<Task> listOfTasks, int taskNumber, String newTaskDescription) {
        Task task = listOfTasks.get(taskNumber - 1);
        task.updateDescription(newTaskDescription);
        return task;

    }

    /**
     * Clone a task to the list of tasks.
     *
     * @param listOfTasks The list of tasks to add the new task to.
     * @param taskNumber The index of task to clone.
     */
    public static void cloneTask(ArrayList<Task> listOfTasks, int taskNumber) {
        Task task = listOfTasks.get(taskNumber - 1);
        listOfTasks.add(task);
    }


}
