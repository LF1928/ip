public class Events extends Task{
    protected String description;
    protected boolean isDone;

    protected String duration;

    public Events(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public String getTypeIcon() {
        return "E";
    }

    private String getStartTime() {
        return "";
    }
    private String getEndTime() {
        return "";
    }

    public String getDuration() {
        return " (from: " + getStartTime() + " to: " + getEndTime() + ") ";
    }

    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description + getDuration();
    }
}
