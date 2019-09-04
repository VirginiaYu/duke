import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> arrList;

    public TaskList(ArrayList<Task> temp) {
        this.arrList = temp;
    }

    public ArrayList<Task> getTask() {
        return arrList;
    }

    public void addTask(Task temp) {
        arrList.add(temp);
    }

    public void removeTask(int index) {
        arrList.remove(index);
    }
}