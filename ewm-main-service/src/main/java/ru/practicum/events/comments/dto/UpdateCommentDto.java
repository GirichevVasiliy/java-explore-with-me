package ru.practicum.events.comments.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateCommentDto {
    @NotNull
    @NotBlank(message = "Поле text должно быть заполнено")
    String text;
}
