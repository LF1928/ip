package duke.tasks;

/**
 * Represents an abstract task in the task management system.
 * A task has a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     * By default, the task is not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon representing whether the task is done.
     * "X" indicates the task is completed, while a space " " indicates it is not.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task,
     * including its completion status and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a string format suitable for file storage.
     *
     * @return A string representation of the task in file format.
     */
    public abstract String toFileFormat();

    /**
     * Parses a task from a given file format string.
     * The format follows: "{Task Type} | {isDone} | {Description} | {Additional Data (if applicable)}".
     *
     * @param line The string representing a task in file format.
     * @return A Task object created from the string, or null if the format is invalid.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Invalid format
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            ToDos todo = new ToDos(description);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            if (parts.length < 4) {
                return null;
            }
            String deadline = parts[3];
            Deadlines deadlineTask = new Deadlines(description, deadline);
            if (isDone) {
                deadlineTask.markAsDone();
            }
            return deadlineTask;
        case "E":
            if (parts.length < 5) {
                return null;
            }
            String startTime = parts[3];
            String endTime = parts[4];
            Events event = new Events(description, startTime, endTime);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            return null; // Unknown task type
        }
    }

}
