package ru.practicum.events.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.users.dto.UserShortDto;

import java.time.LocalDateTime;

@Value
@Builder
public class EventCommentDto {
    String annotation; // example: Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории
    CategoryDto category;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
    Long id;
    UserShortDto initiator; //Пользователь (краткая информация)
    String title; // example: Знаменитое шоу 'Летающая кукуруза'
}
