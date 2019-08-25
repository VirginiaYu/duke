import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am\n" + logo);
        System.out.println("What can I do for you?\n");

        // Declare the object and initialize with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);
        while (true) {
            // String input
            String input = sc.next();
            // Print out corresponding info
            if (input.equals("list") || input.equals("blah")) {
                System.out.println(input+"\n");
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                System.exit(0);
            }
        }
    }
}
