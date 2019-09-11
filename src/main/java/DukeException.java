import java.util.InputMismatchException;

/**
 * Exception Class of duke project
 */

public class DukeException extends InputMismatchException {

    /** Constructor
     * @param message error message
     */
    public DukeException(String message) {
        // Call constructor of parent Exception
        super(message);
    }

}
