import java.util.InputMismatchException;

public class DukeException extends InputMismatchException {

    public DukeException(String message)
    {
        // Call constructor of parent Exception
        super(message);
    }

}
