import java.util.ArrayList;

public enum Command {
    BYE {
        @Override
        void execute(String input, ArrayList<Task> listOfTasks) {
            Ui.bye();
        }
    },
    LIST {
        @Override
        void execute(String input, ArrayList<Task> listOfTasks) {
            Ui.listTasks(listOfTasks);
        }
    },
    MARK {
        @Override
        void execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException {
            int taskNumber = Parser.extractTaskNumber(input);
            TaskList.markAsDone(listOfTasks, taskNumber);
            Ui.markAsDonePrint(listOfTasks, taskNumber);
        }
    },
    UNMARK {
        @Override
        void execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException {
            int taskNumber = Parser.extractTaskNumber(input);
            TaskList.markAsUndone(listOfTasks, taskNumber);
            Ui.markAsUndonePrint(listOfTasks, taskNumber);
        }
    },
    TODO {
        @Override
        void execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException {
            String description = Parser.extractTodoDescription(input);
            Task task = new ToDos(description);
            TaskList.addTask(listOfTasks, task);
            Ui.addTaskPrint(listOfTasks, task);
        }
    },
    DEADLINE {
        @Override
        void execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException {
            String[] details = Parser.extractDeadlineDetails(input);
            Task task = new Deadlines(details[0], details[1]);
            TaskList.addTask(listOfTasks, task);
            Ui.addTaskPrint(listOfTasks, task);
        }
    },
    EVENT {
        @Override
        void execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException {
            String[] details = Parser.extractEventDetails(input);
            Task task = new Events(details[0], details[1], details[2]);
            TaskList.addTask(listOfTasks, task);
            Ui.addTaskPrint(listOfTasks, task);
        }
    },
    DELETE {
        @Override
        void execute(String input, ArrayList<Task> listOfTasks) throws InvalidTaskNumberException {
            int taskNumber = Parser.extractTaskNumber(input);
            Ui.deleteTaskPrint(listOfTasks, taskNumber);
            TaskList.deleteTask(listOfTasks, taskNumber);
        }
    };

    abstract void execute(String input, ArrayList<Task> listOfTasks) throws MissingDescriptionException, InvalidTaskNumberException;
}
