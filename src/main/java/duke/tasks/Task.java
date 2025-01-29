package duke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description; // mark done task with X
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String toFileFormat();
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Invalid format
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                ToDos todo = new ToDos(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                if (parts.length < 4) return null;
                String deadline = parts[3];
                Deadlines deadlineTask = new Deadlines(description, deadline);
                if (isDone) deadlineTask.markAsDone();
                return deadlineTask;
            case "E":
                if (parts.length < 5) return null;
                String startTime = parts[3];
                String endTime = parts[4];
                Events event = new Events(description, startTime, endTime);
                if (isDone) event.markAsDone();
                return event;
            default:
                return null; // Unknown task type
        }
    }

}
