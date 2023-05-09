package ru.practicum.events.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.users.dto.UserShortDto;

import java.time.LocalDateTime;

@Value
@Builder
public class EventFullDto {
    String annotation;
    CategoryDto category;
    Long confirmedRequests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdOn;
    String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Long id;
    UserShortDto initiator; //Пользователь (краткая информация)
    LocationDto location; //Широта и долгота места проведения события
    boolean paid; // Нужно ли оплачивать участие
    int participantLimit; // Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime publishedOn; //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")
    boolean requestModeration; // Нужна ли пре-модерация заявок на участие
    String state; // example: PUBLISHED, Список состояний жизненного цикла события
    String title; // example: Знаменитое шоу 'Летающая кукуруза' Заголовок
    Long views; // Количество просмотрев события

}
