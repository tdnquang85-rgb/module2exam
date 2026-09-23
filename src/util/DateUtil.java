package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateUtil {
    public static final String DATE_PATTERN = "dd/MM/uuuu";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);

    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return false;
        }
        try {
            LocalDate.parse(dateStr.trim(), FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr.trim(), FORMATTER);
    }

    public static boolean isEndAfterOrEqualStart(String startDateStr, String endDateStr) {
        try {
            LocalDate start = parseDate(startDateStr);
            LocalDate end = parseDate(endDateStr);
            return !end.isBefore(start);
        } catch (Exception e) {
            return false;
        }
    }
}
