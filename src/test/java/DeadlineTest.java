package duke.tasks;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void testDeadlinesConstructorWithInvalidDate(){
        String description = "Invalid date task";
        String invalidDeadlineString = "invalid-date";

        assertThrows(DateTimeParseException.class, () -> {
            new Deadlines(description, invalidDeadlineString);
        });
    }

    @Test
    public void testDeadlinesConstructor() {
        String description = "Submit report";
        String deadlineString = "2023-12-31";
        LocalDate expectedDeadline = LocalDate.of(2023, 12, 31);

        Deadlines deadlineTask = new Deadlines(description, deadlineString);

        assertEquals(description, deadlineTask.getDescription());
        assertEquals(expectedDeadline, deadlineTask.getDeadline());
    }
}