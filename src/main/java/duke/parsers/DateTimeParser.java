package duke.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * A utility class for parsing and formatting dates and times.
 * This class supports multiple date-time formats and provides methods to extract
 * date-time strings from commands, parse them into {@link LocalDateTime} or {@link LocalDate},
 * and format them into user-friendly strings.
 */
public class DateTimeParser {
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")
    );

    /**
     * Parses a date-time string into a {@link LocalDateTime} object using the supported formats.
     *
     * @param dateTimeString The date-time string to parse.
     * @return The parsed {@link LocalDateTime} object.
     * @throws DateTimeParseException If the date-time string does not match any of the supported formats.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        throw new DateTimeParseException("Invalid date-time format: Please enter format in d/M/yyyy HHmm" +
                " or d-M-yyyy HH:mm for both start and end date-times", dateTimeString, 0);
    }

    /**
     * Parses a date string into a {@link LocalDate} object using the default format.
     *
     * @param dateString The date string to parse.
     * @return The parsed {@link LocalDate} object.
     * @throws DateTimeParseException If the date string does not match the default format.
     */
    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date format: Please input in yyyy-mm-dd format!", dateString, 0);
        }
    }

    /**
     * Formats a {@link LocalDate} object into a user-friendly string.
     *
     * @param date The {@link LocalDate} object to format.
     * @return The formatted date string (e.g., "Jan 01 2023").
     */
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Formats a {@link LocalDateTime} object into a user-friendly string.
     *
     * @param dateTime The {@link LocalDateTime} object to format.
     * @return The formatted date-time string (e.g., "Jan 01 2023, 2:00 PM").
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
    }

    /**
     * Extracts the date-time portion from a command string.
     * The command is expected to contain a "/by" keyword followed by the date-time string.
     *
     * @param command The command string (e.g., "deadline return book /by 2/12/2019 1800").
     * @return The extracted date-time string, or null if the "/by" keyword is not found.
     */
    public static String extractDateTime(String command) {
        String[] parts = command.split("/by ");
        if (parts.length > 1) {
            return parts[1].trim();
        }
        return null;
    }

    /**
     * Extracts the start and end times from a command string.
     * The command is expected to contain "/from" and "/to" keywords followed by the start and end times.
     *
     * @param command The command string (e.g., "event project meeting /from 2/12/2019 1800 /to 12/2/2019 2100").
     * @return An array containing the start time string and end time string, or null if the keywords are not found.
     */
    public static String[] extractStartAndEndTimes(String command) {
        String[] parts = command.split("/from | /to ");
        if (parts.length == 3) {
            return new String[] { parts[1].trim(), parts[2].trim() };
        }
        return null;
    }

}
