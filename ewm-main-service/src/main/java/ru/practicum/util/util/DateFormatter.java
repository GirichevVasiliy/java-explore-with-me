package ru.practicum.util.util;



import ru.practicum.exception.ValidationDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime formatDate(String date) {
        LocalDateTime newDate = null;
        if (date == null) {
            throw new ValidationDateException("Дата должна быть задана");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            newDate = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new ValidationDateException("Неверный формат даты");
        }
        return newDate;
    }
}
