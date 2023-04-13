package ru.practicum.statsserver.util;

import ru.practicum.statsserver.exception.ValidationDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    private final static String DATA_FORMAT = new String("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime formatDate(String date) {
        LocalDateTime newDate = null;
        if (date == null) {
            throw new ValidationDateException("Дата должна быть задана");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_FORMAT);
            newDate = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new ValidationDateException("Неверный формат даты");
        }
        return newDate;
    }
}
