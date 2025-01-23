import java.util.Scanner;

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
        String[] listOfTasks = new String[100];
        int count = 1;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (String task : listOfTasks) {
                    if (task != null) {
                        System.out.println(task);
                    }
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            System.out.println("____________________________________________________________");
            System.out.println("added: " + userInput);
            System.out.println("____________________________________________________________");
            
            listOfTasks[count-1] = count + ". " + userInput;
            count += 1;
        }

        scanner.close();

    }

}
