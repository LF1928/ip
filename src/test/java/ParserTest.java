package duke.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.MissingDescriptionException;

public class ParserTest {
    @Test
    void testExtractNewTaskDescription_validFormats_expectedBehaviour() throws MissingDescriptionException {
        String userInput = "update 1 Complete the report";
        String expected = "Complete the report";
        assertEquals(expected, Parser.extractNewTaskDescription(userInput));
    }

    @Test
    void testExtractNewTaskDescription_validInputWithMultipleWords_expectedBehaviour()
            throws MissingDescriptionException {
        String userInput = "update 2 Fix the bug in the login module";
        String expected = "Fix the bug in the login module";
        assertEquals(expected, Parser.extractNewTaskDescription(userInput));
    }

    @Test
    void testExtractNewTaskDescription_invalidInputMissingDescription_expectedBehaviour() {
        String userInput = "update 3";
        Exception exception = assertThrows(MissingDescriptionException.class, () -> {
            Parser.extractNewTaskDescription(userInput);
        });
        assertEquals("Invalid task format! Expected: update {taskNumber} {newTaskDescription}", exception.getMessage());
    }

    @Test
    void testExtractNewTaskDescription_invalidInputOnlyUpdateKeyword_expectedBehaviour() {
        String userInput = "update";
        Exception exception = assertThrows(MissingDescriptionException.class, () -> {
            Parser.extractNewTaskDescription(userInput);
        });
        assertEquals("Invalid task format! Expected: update {taskNumber} {newTaskDescription}", exception.getMessage());
    }
}
