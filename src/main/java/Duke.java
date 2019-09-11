import java.util.ArrayList;

/**
 * main class of duke project
 */


public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Initialize duke project
     *
     * @param filePath file path
     * @throws DukeException
     */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try { tasks = new TaskList(storage.load()); }
        catch (DukeException e) {
            ui.showLoadingError(e);
            ArrayList<Task> arrList = new ArrayList<Task>();
            tasks = new TaskList(arrList);
        }
    }

    /** run duke **/
    public void run() {
        ui.showWelcome();
        Parser parser = new Parser(ui, tasks, storage);
        parser.process();

    }
    /** main **/
    public static void main(String[] args){
        new Duke("/Users/yu/duke.txt").run();
    }

}
