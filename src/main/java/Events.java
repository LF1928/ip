import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Events extends Task{

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Events(String description, String[] startEndTimes) {
        super(description);

        this.startTime = DateTimeParse.parseDateTime(startEndTimes[0]);
        this.endTime = DateTimeParse.parseDateTime(startEndTimes[1]);
    }

    public Events(String description, String startTime, String endTime) {
        super(description);

        this.startTime = DateTimeParse.parseDateTime(startTime);
        this.endTime = DateTimeParse.parseDateTime(endTime);
    }

    public String getTypeIcon() {
        return "E";
    }

    private String getStartTime() {
        return DateTimeParse.formatDateTime(this.startTime);
    }
    private String getEndTime() {
        return DateTimeParse.formatDateTime(this.endTime);
    }

    public String getDuration() {
        return " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }

    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description + getDuration();
    }

    @Override
    public String toFileFormat() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + getStartTime()
                + " | " + getEndTime();
    }

}
