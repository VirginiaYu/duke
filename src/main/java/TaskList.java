import java.util.ArrayList;
/**
 * a collection of created tasks
 */

public class TaskList {
    protected ArrayList<Task> arrList;

    /**
     * Constructor
     * @param temp array list of created tasks
     */
    public TaskList(ArrayList<Task> temp) {
        this.arrList = temp;
    }
    /**
     * get task list
     * @return your task list
     */
    public ArrayList<Task> getTask() {
        return arrList;
    }

    /**
     * add task list
     * @param temp task
     */
    public void addTask(Task temp) {
        arrList.add(temp);
    }

    /**
     * remove task
     * @param index task position/index in your task list
     */
    public void removeTask(int index) {
        arrList.remove(index);
    }
}