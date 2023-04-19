package ru.practicum.events.event.dto;

import lombok.Builder;
import lombok.Value;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.events.event.dto.stateDto.EventStateDto;
import ru.practicum.events.event.model.location.Location;
import ru.practicum.users.dto.UserShortDto;

import javax.validation.constraints.Pattern;

@Value
@Builder
public class EventFullDto {
    String annotation; // example: Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории Краткое описание
    CategoryDto category;
    Long confirmedRequests; // Количество одобренных заявок на участие в данном событии
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Invalid date format")
    String createdOn; // Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")
    String description; //Полное описание события
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Invalid date format")
    String eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
    Long id;
    UserShortDto initiator; //Пользователь (краткая информация)
    Location location; //Широта и долгота места проведения события
    boolean paid; // Нужно ли оплачивать участие
    int participantLimit; // Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Invalid date format")
    String publishedOn; //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")
    boolean requestModeration; // Нужна ли пре-модерация заявок на участие
    EventStateDto state; // example: PUBLISHED, Список состояний жизненного цикла события
    String title; // example: Знаменитое шоу 'Летающая кукуруза' Заголовок
    Long views; // Количество просмотрев события

}
