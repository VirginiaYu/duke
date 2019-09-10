import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test for Event Class
public class EventTest {
    @Test
    public void stringConversionTest() {
        Event testEvent = new Event("Test Event", "31/09/2019 1600-1800");
        assertEquals("[E][\u2718] Test Event (at: 31st of September 2019, 4:00-6:00PM)", testEvent.toString());
        assertEquals("  [\u2713] Test Event", testEvent.markAsDone());
        assertEquals("E | 1 | Test Event | 31/9/2019 1600-1800" , testEvent.toTxtFile());
    }
}