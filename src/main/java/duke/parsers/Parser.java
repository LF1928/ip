package duke.parsers;

import duke.command.Command;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingDescriptionException;

/**
 * The {@code Parser} class is responsible for parsing user input commands and extracting relevant details
 * for creating tasks in the task management application.
 * It provides methods for parsing command words, task numbers, descriptions, and task-specific details
 * such as deadlines and event timings.
 */
public class Parser {

    /**
     * Parses the user input to extract the command word and returns the corresponding {@link Command}.
     *
     * @param userInput The full user input provided by the user.
     * @return The corresponding {@link Command} object for the input command word.
     * @throws MissingDescriptionException If the user input is invalid or missing required details.
     * @throws IllegalArgumentException If the command word does not match any known command.
     */
    public static Command parse(String userInput) throws MissingDescriptionException, IllegalArgumentException {
        String[] inputParts = userInput.split(" ", 2);
        String commandWord = inputParts[0].toUpperCase();

        try {
            return Command.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sorry, the command does not exist :<");
        }
    }

    /**
     * Extracts the task number from the user input.
     *
     * @param userInput The full user input provided by the user.
     * @return The task number extracted from the input.
     * @throws InvalidTaskNumberException If the task number is invalid or cannot be parsed.
     */
    public static int extractTaskNumber(String userInput) throws InvalidTaskNumberException {
        try {
            String[] parts = userInput.split(" ");
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid task number!");
        }
    }
    
    /**
     * Extracts the keyword to find from the user input.
     *
     * @param userInput The full user input provided by the user.
     * @return The keyword to find.
     */
    public static String extractKeyword(String userInput) {
        return userInput.substring(5).trim();
    }

    /**
     * Extracts the description of a todo task from the user input.
     *
     * @param userInput The full user input provided by the user.
     * @return The description of the todo task.
     * @throws MissingDescriptionException If the description is missing or empty.
     */
    public static String extractTodoDescription(String userInput) throws MissingDescriptionException {
        if (userInput.length() <= 5) {
            throw new MissingDescriptionException("The description of a todo cannot be empty.");
        }
        return userInput.substring(5).trim();
    }

    /**
     * Extracts the description and deadline details of a deadline task from the user input.
     *
     * @param userInput The full user input provided by the user.
     * @return An array where the first element is the description and the second is the deadline.
     * @throws MissingDescriptionException If the description or deadline is missing or incorrectly formatted.
     */
    public static String[] extractDeadlineDetails(String userInput) throws MissingDescriptionException {
        if (!userInput.contains("/by")) {
            throw new MissingDescriptionException("The deadline of a deadline task cannot be empty.");
        }
        String description = userInput.substring(9, userInput.indexOf("/by")).trim();
        String deadline = DateTimeParse.extractDateTime(userInput);
        return new String[]{description, deadline};
    }

    /**
     * Extracts the description and start/end times of an event task from the user input.
     *
     * @param userInput The full user input provided by the user.
     * @return An array where the first element is the description, the second is the start time,
     *         and the third is the end time.
     * @throws MissingDescriptionException If the description or start/end times are missing or incorrectly formatted.
     */
    public static String[] extractEventDetails(String userInput) throws MissingDescriptionException {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new MissingDescriptionException("The start and end times of an event cannot be empty.");
        }
        String description = userInput.substring(6, userInput.indexOf("/from")).trim();
        String[] startEndTime = DateTimeParse.extractStartAndEndTimes(userInput);
        return new String[]{description, startEndTime[0], startEndTime[1]};
    }
}
