import java.util.Scanner;
/**
 * ui implementation
 */
public class Ui {
    private String value;
    private Scanner sc = new Scanner(System.in);

    /**
     * retreive user input
     * @return user input as string
     */
    public String askForInput() {
        value = sc.nextLine();
        return value;
    }

    /**
     * print out welcome message
     */
    public void showWelcome() {
        // Greetings!
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * print out exit message
     */
    public void byeMsg() {
        System.out.println("Bye. See you soon again!");
    }

    /**
     * print out message after adding a new task
     * the info should include task description,
     * and the number of tasks in the task list
     *
     * @param description task description
     * @param size how many tasks in the task list after addition
     */
    public void addTaskMsg(String description, int size) {
        System.out.println("Got it. I've added this task: \n  " + description);
        System.out.println("Now you have " + size + " tasks in your list.");
    }

    /**
     * print out message after a task is marked as done
     *
     * @param description task description
     */
    public void doneTaskMsg(String description) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(description);
    }

    /**
     * print out message after a task is deleted from the task list
     *
     * @param description task description
     * @param size how many tasks in the task list after deletion
     */

    public void deleteMsg (String description, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(description);
        System.out.println("Now you have " + size + " tasks in your list.");
    }

    /**
     * print out message when listing all tasks in the task list
     */
    public void listMsg() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * print out message when finding related tasks
     *
     */
    public void findMsg() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * print out error message while executing
     *
     * @param exception exception
     */
    public void showLoadingError(DukeException exception) {
        System.out.println(exception.getMessage());
    }
}