package ru.practicum.events.comments.dto;

import lombok.Builder;
import lombok.Value;
import ru.practicum.events.comments.model.CommentState;

import javax.validation.constraints.NotBlank;
@Value
@Builder
public class UpdateCommentAdminDto {
    String text;
    @NotBlank(message = "Поле userId должно быть заполнено")
    Long userId;
    @NotBlank(message = "Поле eventId должно быть заполнено")
    Long eventId;
    CommentStateDto commentStateDto;
}
