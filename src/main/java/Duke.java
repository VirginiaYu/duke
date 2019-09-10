import java.util.ArrayList;


public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            ArrayList<Task> arrList = new ArrayList<Task>();
            tasks = new TaskList(arrList);
        }
    }

    public void run() {
        ui.showWelcome();

        //String userInput = ui.askForInput();
        Parser parser = new Parser(ui, tasks);
        parser.process("enter");

    }
    public static void main(String[] args){
        new Duke("/Users/yu/duke.txt").run();
    }

}
