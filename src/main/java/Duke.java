import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) throws DukeException {

        // Greetings!
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am\n" + logo);
        System.out.println("What can I do for you?");

        // Load info from txt file into the task array
        String filePath = "/Users/yu/duke.txt";
        ArrayList<Task> taskArray = readTxtFileIntoTaskArray(filePath);

        // Declare the object and initialize with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);

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

                // If no task in the list
                if (taskArray.size()==0)
                    System.out.println("No task in your list!");

                // List all task in the list w/ their status
                else {
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < taskArray.size(); i++) {
                        int ptNum = i+1;
                        System.out.println(ptNum+"."+taskArray.get(i).toString());
                    }
                }
            }

            // Mark the task as done
            else if (input.startsWith("done")) {
                // Try and catch block
                try {
                    String doneInfo = checkItem("done", input);
                    int index = Integer.parseInt(doneInfo);
                    taskArray.get(index-1).markAsDone();

                    writeTaskArrayIntoTxtFile(filePath, taskArray);
                }
                catch (DukeException | IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            // Delete item
            else if (input.startsWith("delete")) {

                // Try and catch block
                try {
                    String delInfo = checkItem("delete", input);
                    int index = Integer.parseInt(delInfo);

                    // Print out the delete info
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(taskArray.get(index-1).toString());

                    // Remove the task
                    taskArray.remove(index-1);
                    System.out.println("Now you have "+taskArray.size()+" tasks in the list.");

                    writeTaskArrayIntoTxtFile(filePath, taskArray);

                }
                catch (DukeException | IOException ex) {
                    System.out.println(ex.getMessage());
                }

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
                    taskArray.add(new Event(event, at));

                    // Print out the info of the task
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  "+taskArray.get(taskArray.size()-1).toString());
                    // Show how many tasks are there in the list now
                    System.out.println("Now you have " + taskArray.size() + " tasks in the list.");

                    writeTaskArrayIntoTxtFile(filePath, taskArray);

                }

                catch (DukeException | IOException ex)
                {
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
                    taskArray.add(new Deadline(ddl,by));

                    // Print out the info of the task
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  "+taskArray.get(taskArray.size()-1).toString());
                    // Show how many tasks are there in the list now
                    System.out.println("Now you have " + taskArray.size() + " tasks in the list.");

                    writeTaskArrayIntoTxtFile(filePath, taskArray);


                }

                catch (DukeException | IOException ex)
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
                    taskArray.add(new Todo(todoInfo));

                    // Print out the info of the task
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  "+taskArray.get(taskArray.size()-1).toString());
                    // Show how many tasks are there in the list now
                    System.out.println("Now you have " + taskArray.size() + " tasks in the list.");

                    writeTaskArrayIntoTxtFile(filePath, taskArray);

                }

                catch (DukeException | IOException ex)
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
            String pattern = "event ([a-zA-Z0-9_\\s]+) /at (\\d{1,2}/\\d{1,2}/\\d{4}) [012][0-9][0-6][0-9]-[012][0-9][0-6][0-9]";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"\nTry to follow the time format as 28/08/2019 0942-1153");
            }
            return input.substring(6);
        }
        else if (type.equals("deadline")) {
            String pattern = "deadline ([a-zA-Z0-9_\\s]+) /by (\\d{1,2}/\\d{1,2}/\\d{4}) [012][0-9][0-6][0-9]";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"\nTry to follow the time format as 28/08/2019 0700");
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
        else if (type.equals("done")) {
            String pattern = "done ([0-9]+)";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"");
            }
            return input.substring(5);
        }
        else if (type.equals("delete")) {
            String pattern = "delete ([0-9]+)";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"");
            }
            return input.substring(7);
        }
        else {
            throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"");
        }

    }

    public static ArrayList<Task> readTxtFileIntoTaskArray(String filePath) throws DukeException
    {
        // Create a new task array
        ArrayList<Task> taskArray = new ArrayList<Task>();

        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(reader);

                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] storeInfo = lineTxt.split(" \\| ");
                    // Write the tasks into array
                    switch (storeInfo[0]) {
                        case "E": // Events
                            taskArray.add(new Event(storeInfo[2], storeInfo[3]));
                            if (storeInfo[1].equals("1")) taskArray.get(taskArray.size()-1).isDone=true;
                            break;
                        case "T":  // Todos
                            taskArray.add(new Todo(storeInfo[2]));
                            if (storeInfo[1].equals("1")) taskArray.get(taskArray.size()-1).isDone=true;
                            break;
                        case "D": // Deadlines
                            taskArray.add(new Deadline(storeInfo[2], storeInfo[3]));
                            if (storeInfo[1].equals("1")) taskArray.get(taskArray.size()-1).isDone=true;
                            break;
                        default:
                            break;
                    }
                }
                bufferedReader.close();
                reader.close();
            } else {
                file.createNewFile();
                throw new DukeException("Sorry I cannot find such file.");
            }
        }
        catch (DukeException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        return taskArray;
    }

    public static void writeTaskArrayIntoTxtFile(String filePath, ArrayList<Task> taskArray) throws IOException {
        File file = new File(filePath);
        // File not exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        try {
            if (taskArray.size()!=0)
            for(int i = 0; i < taskArray.size(); i++) {
                out.write(taskArray.get(i).toTxtFile()+"\n");
            }
            out.flush();
            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
