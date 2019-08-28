import java.time.format.DateTimeFormatter;

public class Todo extends Task{

    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toTxtFile() {
        String isDoneInt = this.isDone? "1" : "0";
        return "T | " + isDoneInt + " | " + this.description;
    }
}
