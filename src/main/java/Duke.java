import java.util.Scanner;
import java.util.regex.*;

public class Duke {
    public static void main(String[] args) {
        // Greetings!
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am\n" + logo);
        System.out.println("What can I do for you?");

        // Declare the object and initialize with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);

        // Create a new task array
        Task[] taskArray = new Task[100];

        // Count items in array
        int cnt=0;

        while (true) {

            // String user input
            String input = sc.nextLine();

            // Exit
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }

            // List tasks
            else if (input.equals("list")) {

                // If no task added
                if (cnt==0)
                    System.out.println("No task in your list!");

                // List all task w/ their status
                else {
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < cnt; i++) {
                        int number = i+1;
                        System.out.println(number+"."+taskArray[i].toString());
                    }
                }
            }

            // Mark the task as done
            else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                taskArray[index-1].markAsDone();
            }

            // Add event
            else if (input.startsWith("event")) {

                // Try and catch block
                try {

                    // Check if the right format
                    String eventInfo = checkItem("event", input);

                    // Split the input, and get the event and the event data/time
                    String event = eventInfo.split(" /at ")[0];
                    String at = eventInfo.split(" /at ")[1];

                    // Add the task into the list
                    taskArray[cnt++] = new Event(event,at);

                    // Print out the info of the task
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  "+taskArray[cnt-1].toString());
                    // Show how many tasks are there in the list now
                    System.out.println("Now you have " + cnt + " tasks in the list.");

                }

                catch (DukeException ex)
                {
                    //System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(ex.getMessage());
                }

            }

            else if (input.startsWith("deadline")) {

                // Try and catch block
                try {

                    // Check if the right format
                    String ddlInfo = checkItem("deadline", input);

                    // Split the input, and get the deadline and the ddl data/time
                    String ddl = ddlInfo.split(" /by ")[0];
                    String by = ddlInfo.split(" /by ")[1];

                    // Add the task into the list
                    taskArray[cnt++] = new Deadline(ddl,by);

                    // Print out the info of the task
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  "+taskArray[cnt-1].toString());
                    // Show how many tasks are there in the list now
                    System.out.println("Now you have " + cnt + " tasks in the list.");

                }

                catch (DukeException ex)
                {
                    //System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(ex.getMessage());
                }

            }

            else if (input.startsWith("todo")) {

                // Try and catch block
                try
                {
                    // Check if the right format
                    String todoInfo = checkItem("todo", input);

                    // Add the task into the list
                    taskArray[cnt++] = new Todo(todoInfo);

                    // Print out the info of the task
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  "+taskArray[cnt-1].toString());
                    // Show how many tasks are there in the list now
                    System.out.println("Now you have " + cnt + " tasks in the list.");

                }

                catch (DukeException ex)
                {
                    //System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(ex.getMessage());
                }


            }

            // Deal w/ exceptions
            else{
                try {
                    checkItem("others", input);
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public static String checkItem(String type, String input) throws DukeException {

        if (type.equals("event")) {
            String pattern = "event ([a-zA-Z0-9_\\s]+) /at ([a-zA-Z0-9_\\s]+)";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"");
            }
            return input.substring(6);
        }
        else if (type.equals("deadline")) {
            String pattern = "deadline ([a-zA-Z0-9_\\s]+) /by ([a-zA-Z0-9_\\s]+)";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"");
            }
            return input.substring(9);
        }
        else if (type.equals("todo")) {
            String pattern = "todo ([a-zA-Z0-9_\\s]+)";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"");
            }
            return input.substring(5);
        }
        else {
            throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"");
        }

    }
}
