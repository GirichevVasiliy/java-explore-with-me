package ru.practicum.events.comments.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class InputCommentDto {
    @NotNull
    @NotBlank(message = "Поле text должно быть заполнено")
    String text;
    @NotNull
    @NotBlank(message = "Поле userId должно быть заполнено")
    Long userId;
}
