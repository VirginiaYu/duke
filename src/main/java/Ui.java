import java.util.Scanner;

public class Ui {
    protected String value;
    protected Scanner sc = new Scanner(System.in);

    public String askForInput() {
        value = sc.nextLine();
        return value;
    }

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

    public void byeMsg() {
        System.out.println("Bye. See you soon again!");
    }

    public void addTaskMsg(String description, int size) {
        System.out.println("Got it. I've added this task: \n  " + description);
        System.out.println("Now you have " + size + " tasks in your list.");
    }

    public void doneTaskMsg(String description) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(description);
    }

    public void deleteMsg (String description, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(description);
        System.out.println("Now you have " + size + " tasks in your list.");
    }

    public void listMsg() {
        System.out.println("Here are the tasks in your list:");
    }

    public void findMsg() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void showLoadingError(DukeException exception) {
        System.out.println(exception.getMessage());
    }
}