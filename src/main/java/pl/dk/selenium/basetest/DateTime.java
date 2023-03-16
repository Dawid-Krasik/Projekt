package pl.dk.selenium.basetest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTime {
    private DateTime() {
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    public static String getCurrentLocalDateTime(String format) {
        return formatLocalDateTime(getCurrentLocalDateTime(), format);
    }

    public static String getCurrentTime() {
        return getCurrentLocalDateTime("HH:mm");
    }

    public static String getCurrentDate() {
        return getCurrentLocalDateTime("dd.MM.yyyy");
    }

    public static String getDate(int years, int months, int days) {
        return getDate(years, months, days, "dd.MM.yyyy");
    }

    public static String getDate(int years, int months, int days, String format) {
        return getDateTime(years, months, days, 0, 0, 0, format);
    }

    public static String getDateTime(int years, int months, int days, int hours, int minutes, int seconds, String format) {
        LocalDateTime date = getDateTime(years, months, days, hours, minutes, seconds);
        return formatLocalDateTime(date, format);
    }

    public static String getTime(int hours, int minutes, int seconds, String format) {
        return getDateTime(0, 0, 0, hours, minutes, seconds, format);
    }

    public static LocalDateTime getDateTime(int years, int months, int days, int hours, int minutes, int seconds) {
        LocalDateTime date = getCurrentLocalDateTime();
        date = date.plusYears(years);
        date = date.plusMonths(months);
        date = date.plusDays(days);
        date = date.plusHours(hours);
        date = date.plusMinutes(minutes);
        date = date.plusSeconds(seconds);
        return date;
    }

    /**
     * This method converts the time expressed in milliseconds into a string representing the time in hours, minutes,
     * seconds and milliseconds.
     *
     * @param milliseconds
     * @return String representing the time in hours, minutes, seconds and milliseconds
     */
    public static String getTimeFromMilliseconds(long milliseconds) {
        long millis = milliseconds % 1000;
        long second = (milliseconds / 1000) % 60;
        long minute = (milliseconds / (1000 * 60)) % 60;
        long hour = (milliseconds / (1000 * 60 * 60)) % 24;
        String result = "";
        if (hour > 0) {
            result += hour + "h ";
        }
        if (minute > 0) {
            result += minute + "m ";
        }
        if (second > 0) {
            result += second + "s ";
        }
        if (millis > 0) {
            result += millis + "ms";
        }
        return result.trim();
    }
}

