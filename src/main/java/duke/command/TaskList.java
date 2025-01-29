package duke.command;

import java.util.ArrayList;
import duke.exceptions.*;
import duke.tasks.*;

public class TaskList {
    private static void checkIfTaskIsValid(ArrayList<Task> listOfTasks, int taskNumber) throws InvalidTaskNumberException{
        if (taskNumber <= 0 || taskNumber > listOfTasks.size()) {
            throw new InvalidTaskNumberException("Invalid task number!");
        };
    }
    public static void markAsDone(ArrayList<Task> listOfTasks, int taskNumber) throws InvalidTaskNumberException {
        checkIfTaskIsValid(listOfTasks, taskNumber);
        listOfTasks.get(taskNumber - 1).markAsDone();
    }

    public static void markAsUndone(ArrayList<Task> listOfTasks, int taskNumber) throws InvalidTaskNumberException {
        checkIfTaskIsValid(listOfTasks, taskNumber);
        listOfTasks.get(taskNumber - 1).markAsUndone();
    }

    public static void deleteTask(ArrayList<Task> listOfTasks, int taskNumber) throws InvalidTaskNumberException {
        checkIfTaskIsValid(listOfTasks, taskNumber);
        listOfTasks.remove(taskNumber - 1);
    }


    public static void addTask(ArrayList<Task> listOfTasks, Task task) throws MissingDescriptionException {
        if (task.getDescription().isEmpty()) {
            throw new MissingDescriptionException("Description for the tasks cannot be empty.");
        }
        listOfTasks.add(task);
    }
}
