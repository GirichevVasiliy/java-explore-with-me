package ru.practicum.users.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Builder
public class NewUserRequest { //Данные нового пользователя
    @NotBlank(message = "Поле name не должно быть пустым")
    @Size(max = 255, message = "Максимальное кол-во символов для описания: 255")
    String email;
    @Size(max = 255, message = "Максимальное кол-во символов для описания: 255")
    @NotBlank(message = "Поле name не должно быть пустым")
    String name;
}
