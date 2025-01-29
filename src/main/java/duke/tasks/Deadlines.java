package duke.tasks;

import java.time.LocalDate;
import duke.parsers.DateTimeParse;

public class Deadlines extends Task{
    protected LocalDate deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = DateTimeParse.parseDate(deadline);
    }

    public String getTypeIcon() {
        return "D";
    }

    public String getDeadline() {
        return " (by: " + DateTimeParse.formatDate(this.deadline) + ")";
    }

    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description + getDeadline();
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.deadline;
    }
}
