/**
 * Represent a task that you are going to do
 */

public class Todo extends Task{

    /**
     * Constructor of Todo class
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description); // Call constructor of super class
    }

    /**
     * Override method
     * Returns a descriptive string
     *
     * @return a string that would be printed out in UI
     * [T] task description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Override method
     * Returns an output record
     *
     * @return a string that would be written into file
     * T | 1 or 0 | task description
     */
    public String toTxtFile() {
        String isDoneInt = this.isDone? "1" : "0";
        return "T | " + isDoneInt + " | " + this.description;
    }
}
