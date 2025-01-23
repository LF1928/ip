public class Events extends Task{

    protected String startTime;
    protected String endTime;

    public Events(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTypeIcon() {
        return "E";
    }

    private String getStartTime() {
        return this.startTime;
    }
    private String getEndTime() {
        return this.endTime;
    }

    public String getDuration() {
        return " (from: " + getStartTime() + " to: " + getEndTime() + ") ";
    }

    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description + getDuration();
    }
}
