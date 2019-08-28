public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }

    public String toTxtFile() {
        String isDoneInt = this.isDone? "1" : "0";
        return "E | " + isDoneInt + " | " + this.description + " | " + this.by;
    }
}
