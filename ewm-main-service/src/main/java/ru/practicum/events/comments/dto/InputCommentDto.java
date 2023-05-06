package ru.practicum.events.comments.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class InputCommentDto {
    @NotBlank(message = "Поле text должно быть заполнено")
    String text;
    @NotBlank(message = "Поле userId должно быть заполнено")
    Long userId;
    @NotBlank(message = "Поле eventId должно быть заполнено")
    Long eventId;
}
