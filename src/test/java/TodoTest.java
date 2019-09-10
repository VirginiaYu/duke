import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Test for Todo Class */

public class TodoTest {
    @Test
    public void stringConversionTest(){
        Todo testTodo = new Todo("Test Todo");
        assertEquals("[T][\u2718] Test Todo", testTodo.toString());
        assertEquals("  [\u2713] Test Todo", testTodo.markAsDone());
        assertEquals("T | 1 | Test Todo" , testTodo.toTxtFile());
    }
}