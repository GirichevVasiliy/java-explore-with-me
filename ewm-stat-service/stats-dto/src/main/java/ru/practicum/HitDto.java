package ru.practicum;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class HitDto {
    Long id;
    @NotBlank(message = "Поле \"app\" должно быть заполнено")
    String app;
    @NotBlank(message = "Поле \"uri\" должно быть заполнено")
    String uri;
    @NotBlank(message = "Поле \"ip\" должно быть заполнено")
    String ip;
    @NotBlank(message = "Поле \"timestamp\" должно быть заполнено")
    String timestamp;
}
