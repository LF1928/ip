import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
public class DateTimeParse {
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),  // e.g., 2/12/2019 1800
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"), // e.g., 2-12-2019 18:00
            DateTimeFormatter.ofPattern("d/M/yyyy h:mm a") // e.g., 2/12/2019 6:00 PM
    );

    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("MMM d yyyy"),
            DateTimeFormatter.ofPattern("yyyy-mm-dd")
    );

    public static LocalDateTime parseDateTime(String dateTimeString) {
            for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
                try {
                    return LocalDateTime.parse(dateTimeString, formatter);
                } catch (DateTimeParseException e) {
                    // Continue to the next formatter
                }
            }
            return null;
    }

    public static LocalDate parseDate(String dateString) {
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
