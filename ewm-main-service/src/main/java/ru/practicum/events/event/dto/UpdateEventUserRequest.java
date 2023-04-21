package ru.practicum.events.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import ru.practicum.events.event.dto.stateDto.ActionStateDto;
import ru.practicum.events.event.model.location.Location;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Value
@Builder
public class UpdateEventUserRequest { // Данные для изменения информации о событии. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.
    @Size(min = 20, max = 2000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 2000")
    String annotation; // example: Сап прогулки по рекам и каналам – это возможность увидеть Практикбург с другого ракурсаНовая аннотация
    Long category; // Новая категория
    @Size(min = 20, max = 7000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 7000")
    String description; // Новое описание
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
    LocationDto location; //Широта и долгота места проведения события
    Boolean paid; // Новое значение флага о платности мероприятия
    Integer participantLimit; // Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    Boolean requestModeration; // Нужна ли пре-модерация заявок на участие
    ActionStateDto stateAction;
    String title; // example: Знаменитое шоу 'Летающая кукуруза' Заголовок
}
