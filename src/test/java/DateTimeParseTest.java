package duke.parsers;

import org.junit.jupiter.api.Test;
import duke.parsers.DateTimeParse;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.*;

public class DateTimeParseTest {

    @Test
    public void testParseDateTime_ValidFormats() {
        assertEquals(LocalDateTime.of(2023, 12, 31, 14, 30),
                DateTimeParse.parseDateTime("Dec 31 2023, 2:30 PM"));

        assertEquals(LocalDateTime.of(2023, 12, 31, 18, 0),
                DateTimeParse.parseDateTime("31/12/2023 1800"));

        assertEquals(LocalDateTime.of(2023, 12, 31, 18, 0),
                DateTimeParse.parseDateTime("31-12-2023 18:00"));

        assertEquals(LocalDateTime.of(2023, 12, 31, 18, 0),
                DateTimeParse.parseDateTime("31/12/2023 6:00 PM"));
    }

    @Test
    public void testParseDateTime_InvalidFormat() {
        // Test invalid date-time format
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
            DateTimeParse.parseDateTime("Invalid Date Format");
        });
        assertEquals("Invalid date-time format: Invalid Date Format", exception.getMessage());
    }

    @Test
    public void testParseDateTime_EmptyInput() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
            DateTimeParse.parseDateTime("");
        });
        assertEquals("Invalid date-time format: ", exception.getMessage());
    }

    @Test
    public void testParseDateTime_PartialMatch() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
            DateTimeParse.parseDateTime("Dec 31 2023, 14:30"); // Invalid time format
        });
        assertEquals("Invalid date-time format: Dec 31 2023, 14:30", exception.getMessage());
    }
}