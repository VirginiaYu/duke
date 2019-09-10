/**
 * Represent a general task class
 * without specify which task it is
 * it could be todo, event, and deadline
 */

public class Task {

    protected String description; //task description
    protected boolean isDone; // Done status

    /**
     * Constructor of Task class
     * initialize a task class
     * in default, set its done status as false
     *
     * @param description the description of the task
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * check whether this task is done or not
     *
     * @return task done status
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * return the description of the task
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * mark task as done
     *
     * @return a string with its done status
     */
    public String markAsDone() {
        this.isDone = true;
        return "  [\u2713] "+this.description;
    }

    /**
     * Returns a descriptive string
     *
     * @return a string that would be printed out in UI
     */
    public String toString() {
        return "["+this.getStatusIcon()+"] "+this.description;
    }

    /**
     * Returns an output record
     *
     * @return a string that would be written into file
     */
    public String toTxtFile() { // Write to the txt file
        return "";
    }


}

