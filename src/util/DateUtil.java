package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Lớp tiện ích xử lý và kiểm tra tính hợp lệ của ngày tháng theo định dạng dd/MM/yyyy.
 */
public class DateUtil {
    public static final String DATE_PATTERN = "dd/MM/uuuu";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Kiểm tra xem chuỗi có đúng định dạng ngày dd/MM/yyyy và có phải là ngày thực tế không.
     */
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

    /**
     * Chuyển chuỗi định dạng dd/MM/yyyy sang LocalDate.
     */
    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr.trim(), FORMATTER);
    }

    /**
     * Kiểm tra ngày ra viện có lớn hơn hoặc bằng ngày nhập viện không.
     * @return true nếu ngayRaVien >= ngayNhapVien, ngược lại false.
     */
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
