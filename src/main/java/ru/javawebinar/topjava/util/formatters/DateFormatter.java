package ru.javawebinar.topjava.util.formatters;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateFormatter implements Formatter<LocalDate> {

    private DateTimeFormatter formatter;

    public DateFormatter() {
        formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public String print(LocalDate date, Locale locale) {
        if (date == null) {
            return "";
        }
        return date.format(formatter);
    }

    public LocalDate parse(String formatted, Locale locale) throws ParseException {
        if (formatted == null || formatted.length() == 0) {
            return null;
        }

        return LocalDate.parse(formatted, formatter);
    }
}
