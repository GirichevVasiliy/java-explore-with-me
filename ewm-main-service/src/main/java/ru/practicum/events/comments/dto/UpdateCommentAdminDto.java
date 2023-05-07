package ru.practicum.events.comments.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@Builder
public class UpdateCommentAdminDto {
    @NotBlank(message = "Поле text должно быть заполнено")
    @Size(max = 7000, message = "Максимальное кол-во символов для описания: 7000")
    String text;
    @NotNull(message = "Поле userId должно быть заполнено")
    Long userId;
    @NotNull(message = "Поле eventId должно быть заполнено")
    Long eventId;
    CommentStateDto commentStateDto;
}
