package ru.practicum.events.event.dto;

import ru.practicum.events.event.dto.stateDto.ActionStateDto;
import ru.practicum.events.event.model.ActionState;
import ru.practicum.events.event.model.location.Location;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateEventUserRequest { // Данные для изменения информации о событии. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.
    @Size(min = 20, max = 2000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 2000")
    private String annotation; // example: Сап прогулки по рекам и каналам – это возможность увидеть Практикбург с другого ракурсаНовая аннотация
    private Long category; // Новая категория
    @Size(min = 20, max = 7000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 7000")
    private String description; // Новое описание
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Invalid date format")
    String eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
    Location location; //Широта и долгота места проведения события
    private boolean paid; // Новое значение флага о платности мероприятия
    private int participantLimit; // Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    private boolean requestModeration; // Нужна ли пре-модерация заявок на участие
    ActionStateDto stateAction;
    private String title; // example: Знаменитое шоу 'Летающая кукуруза' Заголовок
}
