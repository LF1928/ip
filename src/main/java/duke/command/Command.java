package duke.command;

import java.util.ArrayList;

import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingDescriptionException;
import duke.parsers.Parser;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;
import duke.ui.Ui;

/**
 * Represents the different commands that can be executed in the Duke application.
 * Each command corresponds to a specific action that modifies or interacts with the task list.
 */
public enum Command {
    BYE {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) {
            return Ui.bye();
        }
    },
    LIST {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) {
            return Ui.listTasks(listOfTasks);
        }
    },
    MARK {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException {
            int taskNumber = Parser.extractTaskNumber(input);
            TaskList.markAsDone(listOfTasks, taskNumber);
            return Ui.markAsDonePrint(listOfTasks, taskNumber);
        }
    },
    UNMARK {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException {
            int taskNumber = Parser.extractTaskNumber(input);
            TaskList.markAsUndone(listOfTasks, taskNumber);
            return Ui.markAsUndonePrint(listOfTasks, taskNumber);
        }
    },
    TODO {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException {
            String description = Parser.extractTodoDescription(input);
            Task task = new ToDos(description);
            TaskList.addTask(listOfTasks, task);
            return Ui.addTaskPrint(listOfTasks, task);
        }
    },
    DEADLINE {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException {
            String[] details = Parser.extractDeadlineDetails(input);
            Task task = new Deadlines(details[0], details[1]);
            TaskList.addTask(listOfTasks, task);
            return Ui.addTaskPrint(listOfTasks, task);
        }
    },
    EVENT {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException {
            String[] details = Parser.extractEventDetails(input);
            Task task = new Events(details[0], details[1], details[2]);
            TaskList.addTask(listOfTasks, task);
            return Ui.addTaskPrint(listOfTasks, task);
        }
    },
    DELETE {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException {
            int taskNumber = Parser.extractTaskNumber(input);
            Task taskToDelete = listOfTasks.get(taskNumber - 1);
            TaskList.deleteTask(listOfTasks, taskNumber);
            return Ui.deleteTaskPrint(listOfTasks, taskToDelete);
        }
    },

    FIND {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) {
            String keyword = Parser.extractKeyword(input);
            return Ui.findTasks(keyword, listOfTasks);
        }
    },

    UPDATE {
        @Override
        public String execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException, MissingDescriptionException {
            int taskNumber = Parser.extractTaskNumber(input);
            String oldDescription = listOfTasks.get(taskNumber - 1).getDescription();
            String newTaskDescription = Parser.extractNewTaskDescription(input);
            Task updatedTask = TaskList.updateTaskDescription(listOfTasks, taskNumber, newTaskDescription);
            return Ui.updateTaskDescriptionPrint(updatedTask, oldDescription);
        }
    };

    public abstract String execute(String input, ArrayList<Task> listOfTasks)
            throws MissingDescriptionException, InvalidTaskNumberException;
}
