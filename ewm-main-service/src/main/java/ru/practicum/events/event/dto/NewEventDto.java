package ru.practicum.events.event.dto;

import lombok.Builder;
import lombok.Value;
import ru.practicum.events.event.model.location.Location;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Value
public class NewEventDto {
    @Size(min = 20, max = 200, message = "Минимальное кол-во символов для описания: 20. Максимальное: 200")
    String annotation; //Краткое описание события
    Long id; // id категории к которой относится событие
    @Size(min = 20, max = 7000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 7000")
    String description; //Полное описание события
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Invalid date format")
    String eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
    Location location; //Широта и долгота места проведения события
    boolean paid; // Нужно ли оплачивать участие
    int participantLimit; // Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    boolean requestModeration; // Нужна ли пре-модерация заявок на участие
    String title; // example: Знаменитое шоу 'Летающая кукуруза' Заголовок
}
