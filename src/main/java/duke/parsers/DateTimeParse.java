package duke.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DateTimeParse {
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),  // e.g., 2/12/2019 1800
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"), // e.g., 2-12-2019 18:00
            DateTimeFormatter.ofPattern("d/M/yyyy h:mm a", Locale.ENGLISH) // e.g., 2/12/2019 6:00 PM
    );

    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("MMM d yyyy"),
            DateTimeFormatter.ofPattern("yyyy-mm-dd")
    );

    public static LocalDateTime parseDateTime(String dateTimeString)  throws DateTimeParseException {
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(dateTimeString, formatter);
                System.out.println("Parsed successfully using: " + formatter);
                return parsedDate;
            } catch (DateTimeParseException e) {
                System.out.println("Failed to parse using: " + formatter);
                // Continue to the next formatter
            }
        }
        throw new DateTimeParseException("Invalid date-time format: " + dateTimeString, dateTimeString, 0);
    }

    public static LocalDate parseDate(String dateString) throws DateTimeParseException{
        return LocalDate.parse(dateString);
    }

    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
    }

    public static String extractDateTime(String command) {
        String[] parts = command.split("/by ");
        if (parts.length > 1) {
            return parts[1].trim();
        }
        return null;
    }

    public static String[] extractStartAndEndTimes(String command) {
        String[] parts = command.split("/from | /to ");
        if (parts.length == 3) {
            return new String[] { parts[1].trim(), parts[2].trim() };
        }
        return null;
    }

}
