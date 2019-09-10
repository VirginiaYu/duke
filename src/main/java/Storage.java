import java.io.*;
import java.util.ArrayList;

/**
 * implement a class that can
 * read the task list from existing txt file
 * and output/write updated task list to file
 */
public class Storage {

    protected String filePath;

    /**
     * Constructor
     * @param file file path
     */
    public Storage(String file) {
        this.filePath = file;
    }

    /**
     * load data from txt file
     *
     * @return a task list that contains info reading from txt file
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException
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
                throw new DukeException("Sorry I cannot find output file. So I have created one for you! :)");
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
        return taskArray;
    }

    /**
     * set file path
     * @param newFilePath new file path
     */
    public void setFilePath(String newFilePath) {
        this.filePath = newFilePath;
    }
}
