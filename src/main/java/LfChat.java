import java.util.Scanner;
import java.util.ArrayList;


public class LfChat {
    public static void main(String[] args) {
        String logo = "  _      ______   _____ _           _   \n"
                + " | |    |  ____| |  __ || |         | |  \n"
                + " | |    | |__    | |    | |__   __| |  \n"
                + " | |    |  __|   | |    | '_ \\ / _` |  \n"
                + " | |____| |      | |___ | | | | (_| |_ \n"
                + " |______|_|      |_____||_| |_|\\__,_(_)\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm LFChat");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput;
        ArrayList<Task> listOfTasks = new ArrayList<>();

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                bye();
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                listTasks(listOfTasks);
                continue;
            }

            if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                markAsDone(listOfTasks, taskNumber);
                continue;
            }

            if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                markAsUndone(listOfTasks, taskNumber);
                continue;
            }
            Task task = new Task(userInput);

            listOfTasks.add(task);
            System.out.println("____________________________________________________________");
            System.out.println("added: " + userInput);
            System.out.println("____________________________________________________________");

        }

        scanner.close();

    }
    public static void listTasks(ArrayList<Task> listOfTasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + listOfTasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void bye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void markAsDone(ArrayList<Task> listOfTasks, int taskNumber) {
        if (taskNumber <= 0 || taskNumber > listOfTasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        listOfTasks.get(taskNumber - 1).markAsDone();

        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + listOfTasks.get(taskNumber - 1).toString());
        System.out.println("____________________________________________________________");
    }
    public static void markAsUndone(ArrayList<Task> listOfTasks, int taskNumber) {
        if (taskNumber <= 0 || taskNumber > listOfTasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        listOfTasks.get(taskNumber - 1).markAsUndone();

        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + listOfTasks.get(taskNumber - 1).toString());
        System.out.println("____________________________________________________________");
    }


}
