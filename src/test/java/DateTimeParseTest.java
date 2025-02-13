package duke.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


public class DateTimeParseTest {

    @Test
    public void testParseDateTime_validFormats_expectedBehavior() {
        assertEquals(LocalDateTime.of(2023, 12, 31, 14, 30),
                DateTimeParser.parseDateTime("12/3/2015 2:30"));

        assertEquals(LocalDateTime.of(2023, 12, 31, 18, 0),
                DateTimeParser.parseDateTime("31/12/2023 1800"));

        assertEquals(LocalDateTime.of(2023, 12, 31, 18, 0),
                DateTimeParser.parseDateTime("31-12-2023 18:00"));

        assertEquals(LocalDateTime.of(2023, 12, 31, 18, 0),
                DateTimeParser.parseDateTime("31/12/2023 6:00"));
    }

    @Test
    public void testParseDateTime_invalidFormat_expectedBehavior() {
        // Test invalid date-time format
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
            DateTimeParser.parseDateTime("Invalid Date Format");
        });
        assertEquals("Invalid date-time format: Please enter format in d/M/yyyy HHmm or d-M-yyyy HH:mm for both start and end date-times", exception.getMessage());
    }

    @Test
    public void testParseDateTime_emptyInput_expectedBehavior() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
            DateTimeParser.parseDateTime("");
        });
        assertEquals("Invalid date format: Please input in yyyy-mm-dd format!", exception.getMessage());
    }

    @Test
    public void testParseDateTime_partialMatch_expectedBehavior() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
            DateTimeParser.parseDateTime("Dec 31 2023, 14:30"); // Invalid time format
        });
        assertEquals("Invalid date-time format: Please enter format in d/M/yyyy HHmm or d-M-yyyy HH:mm for both start and end date-times", exception.getMessage());
    }
}
