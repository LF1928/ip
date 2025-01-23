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
            Task task = new Task(userInput);

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

            if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5);
                task = new ToDos(description);
            }

            if (userInput.startsWith("deadline ")) {
                String description = userInput.substring(9, userInput.indexOf("/by")).trim();
                String deadline = userInput.substring(userInput.indexOf("/by") + 4).trim();
                task = new Deadlines(description, deadline);

            }
            if (userInput.startsWith("event ")) {
                String description = userInput.substring(6, userInput.indexOf("/from")).trim();
                String startTime = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to")).trim();
                String endTime = userInput.substring(userInput.indexOf("/to") + 4).trim();
                task = new Events(description, startTime, endTime);

            }

            listOfTasks.add(task);
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + task.toString());
            System.out.println(" Now you have " + listOfTasks.size() + " tasks in the list.");
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
