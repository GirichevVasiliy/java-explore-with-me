package ru.practicum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class StatsDto {
    @NotBlank(message = "Поле \"app\" должно быть заполнено")
    private final String app; //ewm-main-service
    @NotBlank(message = "Поле \"uri\" должно быть заполнено")
    private final String uri; // /events/1
    private final Long hits; // кол-во
}
