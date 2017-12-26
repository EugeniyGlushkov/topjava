package ru.javawebinar.topjava.util.formatters;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class TimeFormatter implements Formatter<LocalTime> {

    private DateTimeFormatter formatter;

    public TimeFormatter() {
        formatter = DateTimeFormatter.ISO_LOCAL_TIME;
    }

    public String print(LocalTime date, Locale locale) {
        if (date == null) {
            return "";
        }
        return date.format(formatter);
    }

    public LocalTime parse(String formatted, Locale locale) throws ParseException {
        if (formatted == null || formatted.length() == 0) {
            return null;
        }

        return LocalTime.parse(formatted, formatter);
    }
}
