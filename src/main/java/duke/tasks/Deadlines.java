package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.parsers.DateTimeParse;

public class Deadlines extends Task{
    protected LocalDate deadline;

    public Deadlines(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = DateTimeParse.parseDate(deadline);
    }

    public String getTypeIcon() {
        return "D";
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public String deadlineFormat() {
        return " (by: " + DateTimeParse.formatDate(this.deadline) + ")";
    }

    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description + deadlineFormat();
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + getDeadline();
    }
}
