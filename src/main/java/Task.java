public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [\u2713] "+this.description);
    }

    public String toString() {
        return "["+this.getStatusIcon()+"] "+this.description;
    }

    public String toTxtFile() { // Write to the txt file
        return "";
    }


}

