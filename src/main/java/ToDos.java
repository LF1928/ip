public class ToDos extends Task{
    protected String description;
    protected boolean isDone;


    public ToDos(String description) {
        super(description);
    }

    public String getTypeIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
