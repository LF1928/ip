package duke.tasks;
/**
 * Represents a To-Do task in the task management system.
 * A To-Do task is a simple task that only has a description and completion status.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDos task with the given description.
     * By default, the task is not marked as done.
     *
     * @param description The description of the To-Do task.
     */
    public ToDos(String description) {
        super(description);
    }


    /**
     * Returns the type icon representing a To-Do task.
     *
     * @return A string "T" representing the task type.
     */
    public String getTypeIcon() {
        return "T";
    }


    /**
     * Returns a string representation of the To-Do task,
     * including its type, completion status, and description.
     *
     * @return A formatted string representing the To-Do task.
     */
    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the string format used to save the task to a file.
     * The format follows: "T | {1 for done, 0 for not done} | {description}".
     *
     * @return A string representing the To-Do task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

}
