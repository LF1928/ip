package duke.exceptions;

/**
 * Exception thrown when compete task information is missing.
 * This exception is typically used to indicate that the user
 * has provided an empty or null description for a task or that
 * the user has forgotten to provide dates.
 */
public class MissingDescriptionException extends Exception{
    public MissingDescriptionException(String message) {
        super(message);
    }
}
