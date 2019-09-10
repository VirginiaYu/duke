import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Test for Deadline Class
public class DeadlineTest {
    @Test
    public void stringConversionTest() {
        Deadline testDDL = new Deadline("Test Deadline", "31/09/2019 1630");
        assertEquals("[D][\u2718] Test Deadline (by: 31st of September 2019, 4:30PM)", testDDL.toString());
        assertEquals("  [\u2713] Test Deadline", testDDL.markAsDone());
        assertEquals("D | 1 | Test Deadline | 31/9/2019 1630" , testDDL.toTxtFile());
    }
}