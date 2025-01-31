package duke.exceptions;

/**
 * Exception thrown when an invalid task number is provided.
 * This exception is typically used to indicate that a task number
 * is out of bounds or does not exist in the task list.
 */
public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
