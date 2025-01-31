package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.parsers.DateTimeParse;
/**
 * Represents a task with a specific start and end date time.
 * A Event task includes a description and a start end date time.
 */
public class Events extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructs an Events object with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startTime The start time of the event in string format.
     * @param endTime The end time of the event in string format.
     * @throws DateTimeParseException If the provided start or end time cannot be parsed.
     */
    public Events(String description, String startTime, String endTime) throws DateTimeParseException {
        super(description);

        this.startTime = DateTimeParse.parseDateTime(startTime);
        this.endTime = DateTimeParse.parseDateTime(endTime);
    }

    /**
     * Returns the icon representing an event task.
     *
     * @return The icon "E" representing an event task.
     */
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns the formatted start time of the event.
     *
     * @return A string representing the formatted start time of the event.
     */
    private String getStartTime() {
        return DateTimeParse.formatDateTime(this.startTime);
    }

    /**
     * Returns the formatted end time of the event.
     *
     * @return A string representing the formatted end time of the event.
     */
    private String getEndTime() {
        return DateTimeParse.formatDateTime(this.endTime);
    }

    /**
     * Returns the duration of the event in the format.
     *
     * @return A string representing the event's duration.
     */
    public String getDuration() {
        return " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }

    /**
     * Returns a string representation of the event task, including its type, status, description,
     * and duration.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description + getDuration();
    }

    /**
     * Returns a string representation of the event task in a file-friendly format.
     * The format is as follows: "E | {status} | {description} | {start time} | {end time}".
     *
     * @return A string representing the event task in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + getStartTime()
                + " | " + getEndTime();
    }

}
