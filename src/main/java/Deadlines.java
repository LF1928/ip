public class Deadlines extends Task{
    protected String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getTypeIcon() {
        return "D";
    }

    public String getDeadline() {
        return " (by: " + this .deadline + ")";
    }

    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description + getDeadline();
    }
}
