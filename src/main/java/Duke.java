import java.util.Scanner;

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

            // String input
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

                // Split the input, and get the event and the event data/time
                String event = input.substring(6).split(" /at ")[0];
                String at = input.split(" /at ")[1];

                // Add the task into the list
                taskArray[cnt++] = new Event(event,at);

                // Print out the info of the task
                System.out.println("Got it. I've added this task:");
                System.out.println("  "+taskArray[cnt-1].toString());
                // Show how many tasks are there in the list now
                System.out.println("Now you have " + cnt + " tasks in the list.");

            }

            else if (input.startsWith("deadline")) {

                // Split the input, and get the deadline and the ddl data/time
                String ddl = input.substring(9).split(" /by ")[0];
                String by = input.split(" /by ")[1];

                // Add the task into the list
                taskArray[cnt++] = new Deadline(ddl,by);

                // Print out the info of the task
                System.out.println("Got it. I've added this task:");
                System.out.println("  "+taskArray[cnt-1].toString());
                // Show how many tasks are there in the list now
                System.out.println("Now you have " + cnt + " tasks in the list.");

            }

            else if (input.startsWith("todo")) {

                // Split the input, and get the todo
                String todo = input.substring(5);

                // Add the task into the list
                taskArray[cnt++] = new Todo(todo);

                // Print out the info of the task
                System.out.println("Got it. I've added this task:");
                System.out.println("  "+taskArray[cnt-1].toString());
                // Show how many tasks are there in the list now
                System.out.println("Now you have " + cnt + " tasks in the list.");

            }

            // Deal w/ other types of tasks
            else{
                // Print out the added task
                System.out.println("added: "+input);
                // Add user input into string array
                taskArray[cnt++] = new Task(input);
            }
        }
    }
}
