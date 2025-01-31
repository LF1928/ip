package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.parsers.DateTimeParse;

/**
 * Represents a task with a specific deadline.
 * A deadline task includes a description and a due date.
 */
public class Deadlines extends Task {
    protected LocalDate deadline;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline as a string, which will be parsed into a LocalDate.
     * @throws DateTimeParseException If the provided deadline string cannot be parsed.
     */
    public Deadlines(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = DateTimeParse.parseDate(deadline);
    }

    /**
     * Returns the type icon representing a deadline task.
     *
     * @return A string "D" indicating this is a deadline task.
     */
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns the deadline of the task as a LocalDate.
     *
     * @return The deadline date.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Formats the deadline into a user-friendly string.
     *
     * @return A formatted string displaying the deadline.
     */
    public String deadlineFormat() {
        return " (by: " + DateTimeParse.formatDate(this.deadline) + ")";
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type, status, description, and formatted deadline.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description + deadlineFormat();
    }

    /**
     * Converts the deadline task into a string format suitable for file storage.
     *
     * @return A string representation of the task in file format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + getDeadline();
    }
}

