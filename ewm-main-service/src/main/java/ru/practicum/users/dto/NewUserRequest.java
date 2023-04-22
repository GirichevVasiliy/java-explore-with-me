package ru.practicum.users.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@Builder
public class NewUserRequest { //Данные нового пользователя
   // @Email(regexp = "^[a-zA-Z0-9.]+[^._]@[^.\\-_]+[a-zA-Z0-9.]+[a-zA-Z0-9]$", message = "Email введен некорректно")
    String email;
    @NotBlank(message = "Поле name не должно быть пустым")
    String name;
}
