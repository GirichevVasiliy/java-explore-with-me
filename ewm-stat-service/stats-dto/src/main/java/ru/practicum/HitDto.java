package ru.practicum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class HitDto {
    private final Long id;
    @NotBlank(message = "Поле \"app\" должно быть заполнено")
    private final String app;
    @NotBlank(message = "Поле \"uri\" должно быть заполнено")
    private final String uri;
    @NotBlank(message = "Поле \"ip\" должно быть заполнено")
    private final String ip;
    @NotBlank(message = "Поле \"timestamp\" должно быть заполнено")
    private final String timestamp;
}
