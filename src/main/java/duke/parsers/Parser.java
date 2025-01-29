package duke.parsers;

import duke.command.Command;
import duke.exceptions.*;

public class Parser {

    public static Command parse(String userInput) throws MissingDescriptionException, IllegalArgumentException {
        String[] inputParts = userInput.split(" ", 2);
        String commandWord = inputParts[0].toUpperCase();

        try {
            return Command.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sorry, the command does not exist :<");
        }
    }

    public static int extractTaskNumber(String userInput) throws InvalidTaskNumberException {
        try {
            String[] parts = userInput.split(" ");
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid task number!");
        }
    }

    public static String extractTodoDescription(String userInput) throws MissingDescriptionException {
        if (userInput.length() <= 5) {
            throw new MissingDescriptionException("The description of a todo cannot be empty.");
        }
        return userInput.substring(5).trim();
    }

    public static String[] extractDeadlineDetails(String userInput) throws MissingDescriptionException {
        if (!userInput.contains("/by")) {
            throw new MissingDescriptionException("The deadline of a deadline task cannot be empty.");
        }
        String description = userInput.substring(9, userInput.indexOf("/by")).trim();
        String deadline = DateTimeParse.extractDateTime(userInput);
        return new String[]{description, deadline};
    }

    public static String[] extractEventDetails(String userInput) throws MissingDescriptionException {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new MissingDescriptionException("The start and end times of an event cannot be empty.");
        }
        String description = userInput.substring(6, userInput.indexOf("/from")).trim();
        String[] startEndTime = DateTimeParse.extractStartAndEndTimes(userInput);
        return new String[]{description, startEndTime[0], startEndTime[1]};
    }
}