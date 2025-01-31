package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testDeadlinesConstructor_invalidDate_exceptionThrown() {
        String description = "Invalid date task";
        String invalidDeadlineString = "invalid-date";

        assertThrows(DateTimeParseException.class, () -> {
            new Deadlines(description, invalidDeadlineString);
        });
    }

    @Test
    public void testDeadlinesConstructor_expectedBehavior() {
        String description = "Submit report";
        String deadlineString = "2023-12-31";
        LocalDate expectedDeadline = LocalDate.of(2023, 12, 31);

        Deadlines deadlineTask = new Deadlines(description, deadlineString);

        assertEquals(description, deadlineTask.getDescription());
        assertEquals(expectedDeadline, deadlineTask.getDeadline());
    }
}
