package ru.practicum.users.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class NewUserRequest { //Данные нового пользователя
    @NotBlank(message = "Поле email не должно быть пустым")
    String email;
    @NotBlank(message = "Поле name не должно быть пустым")
    String name;
}
