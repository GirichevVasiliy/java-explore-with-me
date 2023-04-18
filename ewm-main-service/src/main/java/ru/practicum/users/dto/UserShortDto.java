package ru.practicum.users.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class UserShortDto {
    Long id;
    @NotBlank(message = "Поле name не должно быть пустым")
    String name; // example: Фёдоров Матвей
}
