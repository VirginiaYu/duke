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
        // Create a new String array
        String[] strLst = new String[100];
        int cnt=0;
        while (true) {
            // String input
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                System.exit(0);
            } else if (input.equals("list")) {
                for(int i = 0; i < cnt; i++) {
                    int number = i+1;
                    System.out.println(number + ". " + strLst[i]);
                }
            } else{
                System.out.println("added: "+ input+"\n");
                // Add user input into string array
                strLst[cnt++] = input;
            }
        }
    }
}
