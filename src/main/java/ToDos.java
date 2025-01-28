public class ToDos extends Task{
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

    @Override
    public String toFileFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

}
