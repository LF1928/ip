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

public enum Command {
    BYE {
        @Override
        public void execute(String input, ArrayList<Task> listOfTasks) {
            Ui.bye();
        }
    },
    LIST {
        @Override
        public void execute(String input, ArrayList<Task> listOfTasks) {
            Ui.listTasks(listOfTasks);
        }
    },
    MARK {
        @Override
        public void execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException {
            int taskNumber = Parser.extractTaskNumber(input);
            TaskList.markAsDone(listOfTasks, taskNumber);
            Ui.markAsDonePrint(listOfTasks, taskNumber);
        }
    },
    UNMARK {
        @Override
        public void execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException {
            int taskNumber = Parser.extractTaskNumber(input);
            TaskList.markAsUndone(listOfTasks, taskNumber);
            Ui.markAsUndonePrint(listOfTasks, taskNumber);
        }
    },
    TODO {
        @Override
        public void execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException {
            String description = Parser.extractTodoDescription(input);
            Task task = new ToDos(description);
            TaskList.addTask(listOfTasks, task);
            Ui.addTaskPrint(listOfTasks, task);
        }
    },
    DEADLINE {
        @Override
        public void execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException {
            String[] details = Parser.extractDeadlineDetails(input);
            Task task = new Deadlines(details[0], details[1]);
            TaskList.addTask(listOfTasks, task);
            Ui.addTaskPrint(listOfTasks, task);
        }
    },
    EVENT {
        @Override
        public void execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException {
            String[] details = Parser.extractEventDetails(input);
            Task task = new Events(details[0], details[1], details[2]);
            TaskList.addTask(listOfTasks, task);
            Ui.addTaskPrint(listOfTasks, task);
        }
    },
    DELETE {
        @Override
        public void execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException {
            int taskNumber = Parser.extractTaskNumber(input);
            Ui.deleteTaskPrint(listOfTasks, taskNumber);
            TaskList.deleteTask(listOfTasks, taskNumber);
        }
    },

    FIND {

        @Override
        public void execute(String input, ArrayList<Task> listOfTasks) {
            String keyword = Parser.extractKeyword(input);
            Ui.findTasks(keyword, listOfTasks);
        }
    };

    public abstract void execute(String input, ArrayList<Task> listOfTasks)
            throws MissingDescriptionException, InvalidTaskNumberException;
}
