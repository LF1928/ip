package duke.ui;

import java.util.ArrayList;
import duke.tasks.*;
import duke.exceptions.*;

public class Ui {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

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


    public static void markAsDonePrint(ArrayList<Task> listOfTasks, int taskNumber) {

        Task task = listOfTasks.get(taskNumber - 1);
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

    public static void markAsUndonePrint(ArrayList<Task> listOfTasks, int taskNumber) {
        Task task = listOfTasks.get(taskNumber - 1);
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printLine();
    }

    public static void deleteTaskPrint(ArrayList<Task> listOfTasks, int taskNumber) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + listOfTasks.get(taskNumber - 1));
        System.out.println(" Now you have " + (listOfTasks.size() - 1) + " tasks in the list.");
        printLine();
    }

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

    public static void bye() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void listTasks(ArrayList<Task> listOfTasks) {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + listOfTasks.get(i).toString());
        }
        printLine();
    }
}
