import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private Ui ui;
    private TaskList list;
    protected String filePath;

    public Parser(Ui temp1, TaskList temp2) {
        this.ui = temp1;
        this.list = temp2;
        this.filePath = "/Users/yu/duke.txt";
    }

    public void process(String value) throws DukeException {
        String userInput;
        String[] temp, temp1;
        while (true) {
            try {
                value = ui.askForInput();

                // Exit
                if (value.equals("bye")) {
                    ui.byeMsg();
                    System.exit(0);
                }

                // List
                else if (value.startsWith("list")) {
                    if (list.getTask().size()==0) { // If no task in the list
                        System.out.println("No task in your list!");
                    } else { // List all tasks in the list w/ their status
                        ui.listMsg();
                        for(int i = 0; i < list.getTask().size(); i++) {
                            System.out.println((i+1)+"."+list.getTask().get(i).toString());
                        }
                    }
                }

                // Find
                else if (value.startsWith("find")) {
                    try {
                        String regex = checkItem("find", value);
                        Pattern pattern = Pattern.compile(regex);

                        if (list.getTask().size()==0)
                            System.out.println("No task in list. No matching.");
                        else {
                            ui.findMsg();
                            int cnt = 0;
                            for(int i = 0; i < list.getTask().size(); i++) {
                                String strToBeMatched = list.getTask().get(i).description;
                                Matcher matcher = pattern.matcher(strToBeMatched);
                                if (matcher.find()) {
                                    cnt++;
                                    System.out.println(cnt+"."+list.getTask().get(i).toString());
                                }
                            }
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // Mark as Done
                else if (value.startsWith("done")) {
                    try {
                        String doneInfo = checkItem("done", value);
                        int index = Integer.parseInt(doneInfo) - 1;
                        if (index < 0 | index >= list.getTask().size()) {
                            System.out.println("Sorry. Index invalid.");
                        } else {
                            String description = list.getTask().get(index).markAsDone();
                            ui.doneTaskMsg(description);
                            // Update txt file
                            writeTaskArrayIntoTxtFile(filePath, list);
                        }
                    }
                    catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    catch (DukeException ex){
                        System.out.println(ex.getMessage());
                    }
                }

                // Delete
                else if (value.startsWith("delete")) {
                    try {
                        String delInfo = checkItem("delete", value);
                        int index = Integer.parseInt(delInfo) - 1;
                        if (index < 0 | index >= list.getTask().size()) {
                            System.out.println("Sorry. Index invalid.");
                        } else {
                            String description = list.getTask().get(index).toString();
                            int size = list.getTask().size() - 1;
                            list.removeTask(index); // Remove the task
                            // Print out Msg
                            ui.deleteMsg(description, size);
                            // Update txt file
                            writeTaskArrayIntoTxtFile(filePath, list);
                        }
                    }
                    catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    catch (DukeException ex){
                        System.out.println(ex.getMessage());
                    }
                }

                // Add todos
                else if (value.startsWith("todo")) {
                    try {
                        // Check if the right format
                        String todoInfo = checkItem("todo", value);

                        // Add the task into the list
                        Todo todo = new Todo(todoInfo);
                        list.addTask(todo);

                        // Print out Msg
                        ui.addTaskMsg(todo.toString(), list.getTask().size());

                        // Update txt file
                        writeTaskArrayIntoTxtFile(filePath, list);

                    }

                    catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    } catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                // Add deadlines
                else if (value.startsWith("deadline")) {
                    try {
                        // Check if the right format
                        String ddlInfo = checkItem("deadline", value);

                        // Split the input, and get the deadline and the ddl data/time
                        String ddl = ddlInfo.split(" /by ")[0];
                        String by = ddlInfo.split(" /by ")[1];

                        // Add the task into the list
                        Deadline deadline = new Deadline(ddl,by);
                        list.addTask(deadline);

                        // Print out the info of the task
                        ui.addTaskMsg(deadline.toString(),list.getTask().size());

                        // Update txt file
                        writeTaskArrayIntoTxtFile(filePath, list);
                    }

                    catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    } catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                // Add events
                else if (value.startsWith("event")) {
                    try {
                        // Check if the right format
                        String eventInfo = checkItem("event", value);

                        // Split the input, and get the event and the event data/time
                        String ev = eventInfo.split(" /at ")[0];
                        String at = eventInfo.split(" /at ")[1];

                        // Add the task into the list
                        Event event = new Event(ev, at);
                        list.addTask(event);

                        // Print out the info of the task
                        ui.addTaskMsg(event.toString(),list.getTask().size());

                        // Update txt file
                        writeTaskArrayIntoTxtFile(filePath, list);
                    }

                    catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else {
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Check whether user input is in the right format
    // by using regular expression to match the pattern
    public static String checkItem(String type, String input) throws DukeException {

        if (type.equals("event")) {
            String pattern = "event ([a-zA-Z0-9_\\s]+) /at (\\d{1,2}/\\d{1,2}/\\d{4}) [012][0-9][0-6][0-9]-[012][0-9][0-6][0-9]";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\nTry to follow the format as \"event some_task /at 28/08/2019 0945-1350");
            }
            return input.substring(6);
        }
        else if (type.equals("deadline")) {
            String pattern = "deadline ([a-zA-Z0-9_\\s]+) /by (\\d{1,2}/\\d{1,2}/\\d{4}) [012][0-9][0-6][0-9]";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\nPlease follow the format as \"deadline some_task /by 28/08/2019 2100\"");
            }
            return input.substring(9);
        }
        else if (type.equals("todo")) {
            String pattern = "todo ([a-zA-Z0-9_\\s]+)";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\nPlease follow the format as \"todo some_task\"");
            }
            return input.substring(5);
        }
        else if (type.equals("done")) {
            String pattern = "done ([0-9]+)";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\nPlease follow the format as \"done some_number\"");
            }
            return input.substring(5);
        }
        else if (type.equals("delete")) {
            String pattern = "delete ([0-9]+)";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\nPlease follow the format as \"delete some_number\"");
            }
            return input.substring(7);
        }
        else if (type.equals("find")){
            String pattern = "find ([a-zA-Z0-9_\\s]+)";
            if (!Pattern.matches(pattern, input)) {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\nPlease follow the format as \"find some_description\".");
            }
            return input.substring(5);
        }
        else {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }


    public static void writeTaskArrayIntoTxtFile(String filePath, TaskList taskArray) throws IOException {
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
            if (taskArray.getTask().size()!=0)
                for(int i = 0; i < taskArray.getTask().size(); i++) {
                    out.write(taskArray.getTask().get(i).toTxtFile()+"\n");
                }
            out.flush();
            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
