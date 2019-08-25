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
                        System.out.println(number+".["+taskArray[i].getStatusIcon()+"] "+taskArray[i].getDescription());
                    }
                }
            }

            // Mark the task as done
            else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                taskArray[index-1].markAsDone();
            }

            // Add item
            else{
                System.out.println("added: "+input);
                // Add user input into string array
                taskArray[cnt++]= new Task(input);
            }
        }
    }
}



//list
//    ____________________________________________________________
//     Here are the tasks in your list:
//     1.[✓] read book
//     2.[✗] return book
//     3.[✗] buy bread
//    ____________________________________________________________
//
//done 2
//    ____________________________________________________________
//     Nice! I've marked this task as done:
//       [✓] return book
//